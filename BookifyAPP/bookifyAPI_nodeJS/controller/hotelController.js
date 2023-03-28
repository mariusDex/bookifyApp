
const pool = require("../database/index")
const SQL_SELECT = 'SELECT * FROM HOTEL WHERE (1=1) ';
const SQL_UPDATE = 'UDPATE HOTEL SET';
// -------------------------> AQUÍ DESARROLLAREMOS TODOS LOS MÉTODOS QUE NOS HAGA FALTA <------------------

// NOTAS : Por supuesto podemos tener varios controllers (lo suyo sería tener 1 POR TABLA para tener todo estructurado, a mode de EmployeeDAO, ProductosDAO etc...)
const hotelController = {

    getAll: async (req,res) => {
        try {
            const { id ,name, city,country,check_in, check_out } = req.params;
            
            console.log('Variables : ' + country);
            var sql = SQL_SELECT;
            var arrayCampos = new Array();
            
            if(id !=0 && id > 0){
                console.log('Filtrando por ID');
                sql += ' AND hotel_id = ? ' ;
                arrayCampos.push(id);
            }
            if(name != 'null' && name != undefined){
                console.log('Filtrando por NAME');
                sql += ' AND name = ? ';
                arrayCampos.push(name);
            }
            if(city != 'null' && city != undefined){
                console.log('Filtrando por CITY');
                sql += ' AND city = ? ';
                arrayCampos.push(city);
            }
            if(country != 'null'  && country != undefined){
                console.log('Filtrando por COUNTRY');
                sql += ' AND country = ? ';
                arrayCampos.push(country);
            }
            if(check_in != 'null' && check_in != undefined){
                sql += ' AND check_in = ? ';
                arrayCampos.push(check_in);
            }
            if(check_out != 'null' && check_out != undefined){
                sql += ' AND check_out = ? ';
                arrayCampos.push(check_out);
            }
            if(arrayCampos.length == 0){
                console.log('Array VACIO');
                console.log("Haciendo query BASICA " + SQL_SELECT);
                var [rows, fields] = await pool.query(SQL_SELECT); 
            }else{
                console.log('Array LLENO');
                console.log('Array : ' + arrayCampos.toString());
                console.log('Ejecutando query : ' + sql );
                var [rows, fields] = await pool.query(sql,arrayCampos); 
            }
            
            // queremos que nos devuelva las ROWS que salen de la query : 
            res.json({
                data : rows 
            })
            
        } catch (error) {
            console.log(error)
            res.json({
                status: "error"
            })
        }
    },
    getParametrizedHotels : async (req,res) => {
        try {
            const { check_in,check_out,country,city,guests,top_hotels } = req.params;
            var sql = "";
            var arrayCampos = new Array();
            if(top_hotels == "YES"){
                /*
                sql += "SELECT h.*, COUNT(b.booking_id) AS bookings "
                sql += "FROM HOTEL h "
                sql += "JOIN ROOM r ON r.hotel_id = h.hotel_id "
                sql += "LEFT JOIN BOOKING b ON b.room_id = r.room_id "
                sql += "WHERE h.country = ? ";
                arrayCampos.push(country);

                if(city != "null"){
                    sql += "AND h.city = ? "
                    arrayCampos.push(city);
                }
                
                sql += "AND r.people >= ? "
                arrayCampos.push(guests);
                sql += "AND b.entry_date NOT BETWEEN ? AND ? "
                arrayCampos.push(check_out);
                arrayCampos.push(check_in);
                sql += "AND b.exit_date NOT BETWEEN ? AND ? "
                arrayCampos.push(check_in);
                arrayCampos.push(check_out);
                sql += "GROUP BY h.hotel_id "
                sql += "ORDER BY bookings DESC "
                sql += "LIMIT 10; "*/

                sql +="SELECT H.*, COUNT(*) AS num_bookings "
                arrayCampos.push(guests);
                sql +="FROM HOTEL H "
                sql +="INNER JOIN ROOM R ON H.hotel_id = R.hotel_id "
                sql +="LEFT JOIN BOOKING B ON R.room_id = B.room_id AND R.people >= ? AND (B.entry_date BETWEEN ? AND ? OR B.exit_date BETWEEN ? AND ?) "
                arrayCampos.push(check_in);
                arrayCampos.push(check_out);
                arrayCampos.push(check_in);
                arrayCampos.push(check_out);
                sql +="WHERE H.country = ?";
                arrayCampos.push(country);
                if(city != "null"){
                    sql += "AND H.city = ? "
                    arrayCampos.push(city);
                }

                
                sql +="GROUP BY H.hotel_id "
                sql +="ORDER BY num_bookings DESC "
                sql +="LIMIT 10; "

                
            }else{
                /*
                sql += "SELECT h.* "
                sql += "FROM HOTEL h "
                sql += "WHERE h.hotel_id IN ( "
                sql +=     "SELECT r.hotel_id "
                sql +=     "FROM ROOM r "
                sql +=     "WHERE r.people >= ? "
                arrayCampos.push(guests);
                sql +=     "AND r.hotel_id IN ( "
                sql +=         "SELECT b.room_id "
                sql +=         "FROM BOOKING b "
                sql +=         "WHERE "
                sql +=             "b.entry_date NOT BETWEEN ? AND ? "
                arrayCampos.push(check_out)
                arrayCampos.push(check_in)
                sql +=             "AND exit_date NOT BETWEEN ? AND ? "
                arrayCampos.push(check_in);
                arrayCampos.push(check_out);
                sql +=      ") "
                sql += ") " 
                if(city != "null"){
                    sql += "AND h.city = ? "
                    arrayCampos.push(city);
                }
                sql += "AND h.country = ? "
                arrayCampos.push(country);*/

                sql += "SELECT h.* "
                sql += "FROM HOTEL h "
                sql += "WHERE h.country = ? " 
                arrayCampos.push(country); 
                if(city != "null"){
                    sql += "AND h.city = ? "
                    arrayCampos.push(city);
                }
                 
                sql += "AND h.hotel_id IN ( "
                sql += "SELECT r.hotel_id "
                sql += "FROM ROOM r " 
                sql += "WHERE r.room_id NOT IN ( "
                sql += "SELECT b.room_id " 
                sql += "FROM BOOKING b " 
                sql += "WHERE (b.entry_date <= ? AND b.exit_date >= ? ) " 
                arrayCampos.push(check_in);
                arrayCampos.push(check_in);
                sql += "OR (b.entry_date <= ? AND b.exit_date >= ?) "
                arrayCampos.push(check_out);
                arrayCampos.push(check_out);
                sql += "OR (b.entry_date >= ? AND b.exit_date <= ?) "
                arrayCampos.push(check_in);
                arrayCampos.push(check_out);
                sql += ") "
                sql += "AND r.people >= ? "
                arrayCampos.push(guests);
                sql += ")  ORDER BY h.score DESC"
                                
            }
            
            const [rows, fields]  = await pool.query(sql,arrayCampos);

            res.json({
                data : rows
            })
        } catch (error) {
            console.log(error)
            res.json({
                status: "Error al devolver hoteles con más reservas"
            })
        }
    },
    create: async (req,res) => {
        try {
            // http://localhost:3000/api/v1/posts/mariusz/broza/18 PARA CREAR SEGÚN EL PATRON EN ROUTER
            const { nombre, apellido, edad } = req.params
            const sql = "INSERT INTO employee (nombre,apellido,edad) VALUES(?,?,?)"
            const [rows, fields] = await pool.query(sql,[nombre, apellido, edad])
            res.json({
                data: rows
            })
        } catch (error) {
            console.log(error)
            res.json({
                status: "error"
            })
        }
    },
    update: async (req,res) => {
        try {
            const { nombre, apellido, edad, id} = req.params
            const sql = "UPDATE employee SET nombre = ?, apellido = ?, edad = ? WHERE id = ?"
            const [rows,fields] = await pool.query(sql,[nombre, apellido, edad, id])
            // respuesta
            res.json({
                data : rows // lo que queremos que nos devuelva , es decir, las filas afectadas etc
            })
        } catch (error) {
            console.log(error)
            res.json({
                status: "error"
            })    
        }
    },
    delete : async (req,res) => {
        try {
            const { id } = req.params
            const sql = "DELETE FROM employee WHERE id = ?"
            const [rows,fields] = await pool.query(sql,[id])

            res.json({
                data : rows
            })
        } catch (error) {
            console.log(error)
            res.json({
                status: "error"
            })  
        }
    }

}

module.exports = hotelController