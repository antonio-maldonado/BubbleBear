
 // solicitud post 
const url = "https://admin-kqcu.onrender.com/api/products"; 

async function postProduct (product) {

    try {
        const response = await fetch (url, {
            method: "POST", 
            headers: { 'Content-Type': 'application/json' ,
        }
        ,
        body: JSON.stringify(product),
    });    
        if (response.ok) {
            const data = await response.json();
        } else {
            console.error("Error al crear producto");
        }
    }catch (error) {
        console.error("Error de red: ", error);
    } 
}

















/* solicitus get */



/* async function getAllProducts(){
const url = " https://admin-kqcu.onrender.com/api/products"
    try {
        
        const responseJson = await fetch(url);
        const resolve = await responseJson.json()
        console.log(resolve);
        
    } catch (error) {
        console.log(error);
    }


}

getAllProducts() */


