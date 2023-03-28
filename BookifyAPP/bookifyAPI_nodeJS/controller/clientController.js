const pool = require("../database/index")
const SQL_SELECT = 'SELECT * FROM CLIENT WHERE (1=1) ';
const SQL_UPDATE = 'UDPATE ROOM SET';
// -------------------------> AQUÍ DESARROLLAREMOS TODOS LOS MÉTODOS QUE NOS HAGA FALTA <------------------

// NOTAS : Por supuesto podemos tener varios controllers (lo suyo sería tener 1 POR TABLA para tener todo estructurado, a mode de EmployeeDAO, ProductosDAO etc...)
const userController = {

    getUser: async (req,res) => {
        try {
            const { email,password } = req.params;
            
            var sql = SQL_SELECT;
            sql += " AND email = ? AND password = ?";
            console.log('QUERY USER : ' + email);
            var [rows, fields] = await pool.query(sql,[email,password]); 
           
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


module.exports = userController;