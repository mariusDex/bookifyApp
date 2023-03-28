const pool = require("../database/index")
const SQL_SELECT = 'SELECT * FROM ROOM WHERE (1=1) ';
const SQL_UPDATE = 'UDPATE ROOM SET';
const SQL_INSERT = 'INSERT INTO BOOKING(`client_id`,`room_id`,`guests`,`score`,`date`,`entry_date`,`exit_date`,`description`) VALUES'
// -------------------------> AQUÍ DESARROLLAREMOS TODOS LOS MÉTODOS QUE NOS HAGA FALTA <------------------

// NOTAS : Por supuesto podemos tener varios controllers (lo suyo sería tener 1 POR TABLA para tener todo estructurado, a mode de EmployeeDAO, ProductosDAO etc...)
const bookingController = {

    insertBooking: async (req,res) => {
        try {
            const { client_id, room_id, guests , check_in, check_out, description} = req.params;
            var arrayCampos = new Array();
            var sql = SQL_INSERT;
            arrayCampos.push(client_id);
            arrayCampos.push(room_id);
            arrayCampos.push(guests);
            arrayCampos.push(check_in);
            arrayCampos.push(check_out);
            arrayCampos.push(description);
            sql += "(?,?,?,100,CURDATE(),?,?,?)"; 
           
            var [rows, fields] = await pool.query(sql,arrayCampos); 
            console.log("HACIENDO INSERT")
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
    getClientBookings: async (req,res) => {
        try {
            const { client_id } = req.params;
            var arrayCampos = new Array();
            var sql ="";
            sql += "SELECT B.booking_id, H.name,  B.guests, DATE_FORMAT(B.date, '%Y-%m-%d') AS date, DATE_FORMAT(B.entry_date, '%Y-%m-%d') AS entry_date, DATE_FORMAT(B.exit_date, '%Y-%m-%d') AS exit_date "
            sql += "FROM BOOKING B "
            sql += "INNER JOIN ROOM R ON B.room_id = R.room_id "
            sql += "INNER JOIN HOTEL H ON R.hotel_id = H.hotel_id "
            sql += "WHERE B.client_id = ? "
            arrayCampos.push(client_id);

            console.log("Haciendo query de booking para cliente con ID : " + client_id)
            var [rows, fields] = await pool.query(sql,arrayCampos); 
            console.log("HACIENDO INSERT")
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


module.exports = bookingController;