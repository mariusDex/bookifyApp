const express = require("express")
const router = express.Router()

// Ruta donde tenemos el controller en este proyecto
const hotelController = require("../controller/hotelController");
const roomController = require("../controller/roomController");
const clientController = require("../controller/clientController");
const bookingController = require("../controller/bookingController");
// --- ESTO es como el Controller?ACTION=Productos.FIND_ALL ---
// Según lo que pongas después de "posts" en el localhost:3000/api/v2/posts/ hace una cosa u otra 
// definida en posts.controller

// CASE :  localhost:3000/booking/hoteles
//router.get("/hotels/:id/:name/:city/:country/:check_in/:check_out", hotelController.getAll)    
// BUSQUEDA DE HOTELES COMPLETA Y PARAMETRIZADA SEGÚN INTERFACE
router.get("/hotels/:check_in/:check_out/:country/:city/:guests/:top_hotels",hotelController.getParametrizedHotels);
       // esto es como en el DAO. --> query()
// CASE :  localhost:3000/apiejemplo/posts/2
router.get("/hotels", hotelController.getAll);
router.get("/rooms/:hotel_id/:check_in/:check_out/:guests", roomController.getHotelRooms);
router.post("/booking/:client_id/:room_id/:guests/:check_in/:check_out/:description", bookingController.insertBooking);
router.get("/booking/:client_id", bookingController.getClientBookings);
router.get("/user/:email/:password", clientController.getUser);
// CASE :  localhost:3000/apiejemplo/mariusz/broza/18
router.post("/:nombre/:apellido/:edad", hotelController.create) // ---> insert()
// CASE :  localhost:3000/apiejemplo/update/mariusz2/broza2/23/1
router.put("/update/:nombre/:apellido/:edad/:id", hotelController.update) // ---> update()
router.delete("/:id", hotelController.delete) // --> delete()



// Si os fijáis DIFERENCIA ENTRE : { GET , POST , PUT , DELETE }, por lo tanto, puede haber dos casos iguales, pero si es post o get hacen cosas distintas
// Ejemplo : router.get("/:id", hotelController.getById) ----> Devuelve un registro por ID 
//           router.post("/:id", hotelController.updateRegistro) ----> updatea un registro con esa ID

// Si la llamada es POST --> se ejecuta el router.post
// Si la llamada es GET  --> se ejecuta el router.get

module.exports = router