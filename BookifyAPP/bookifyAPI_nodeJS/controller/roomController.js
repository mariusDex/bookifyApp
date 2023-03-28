const pool = require("../database/index")
const SQL_SELECT = 'SELECT * FROM ROOM WHERE (1=1) ';
const SQL_UPDATE = 'UDPATE ROOM SET';
// -------------------------> AQUÍ DESARROLLAREMOS TODOS LOS MÉTODOS QUE NOS HAGA FALTA <------------------

// NOTAS : Por supuesto podemos tener varios controllers (lo suyo sería tener 1 POR TABLA para tener todo estructurado, a mode de EmployeeDAO, ProductosDAO etc...)
const roomController = {

    getHotelRooms: async (req,res) => {
        try {
            const { hotel_id, check_in, check_out, guests } = req.params;
            var arrayCampos = new Array();
            var sql = "";
             
            /*
            sql += "SELECT * "
            sql += "FROM ROOM "
            sql += "WHERE hotel_id = ? "
            arrayCampos.push(hotel_id);
            sql += "AND people >= ? "
            arrayCampos.push(guests);
            sql += "AND room_id  IN ( "
            sql += "SELECT DISTINCT room_id "
            sql += "FROM BOOKING WHERE hotel_id = ? "
            arrayCampos.push(hotel_id);
            sql += "AND entry_date NOT BETWEEN ? AND ? "
            arrayCampos.push(check_out);
            arrayCampos.push(check_in);
            sql += "AND exit_date NOT BETWEEN ? AND ? " 
            arrayCampos.push(check_in);
            arrayCampos.push(check_out);
            sql += ")"

            console.log("guests : " + guests);
            console.log("hotel : " + hotel_id);
            console.log("check_in : " + check_in);
            console.log("check_Out :" + check_out);*/

            sql += "SELECT * "
            sql += "FROM ROOM "
            sql += "WHERE hotel_id = ? "
            arrayCampos.push(hotel_id);
            sql += "AND people >= ? "
            arrayCampos.push(guests);
            sql += "AND room_id NOT IN ( "
            sql += "SELECT room_id "
            sql += "FROM BOOKING "
            sql += "WHERE (entry_date BETWEEN ? AND ?) "
            arrayCampos.push(check_in);
            arrayCampos.push(check_out);
            sql += "OR (exit_date BETWEEN ? AND ?) "
            arrayCampos.push(check_in);
            arrayCampos.push(check_out);
            sql += ") "


            
            
            var [rows, fields] = await pool.query(sql,arrayCampos); 
           
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
    }
};


module.exports = roomController;