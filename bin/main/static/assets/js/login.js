const form = document.getElementById("formLogin");
if(localStorage.getItem("login")){
  location.assign("/")
}

const postUser = async (userData) => {
const url = "http://localhost:8080/login";

const response = await fetch(url, {
  method: "POST", // POST, PUT, DELETE, GET
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(userData),
  //mode: 'no-cors',
});

  // for (let [key, value] of response.headers) {
  //       console.log(`${key} = ${value}`);
  //     }

      const jwt = await response.json();
      console.log(jwt);
      //jwt != null && localStorage.setItem("token", jwt.token);
      if(jwt!=null){
         localStorage.setItem("token", jwt.token);
        return response.status;
      }else{
        return response.status;
      }
    };

form.addEventListener("submit", async function(event){

    const infoErrorBox = document.getElementById('form-error-info');
    let message = []

    const email = document.getElementById("correo");
    const password = document.getElementById("contrasena");
    const emailError = document.getElementById("error-email");
    const passwordError = document.getElementById("error-password");
    
    if( !email.value ){
        message.push("<p class='alert'>Ingresa tu email </p>")
        emailError.innerHTML = "<p class='alert'>Ingresa tu email </p>";
    }else{
        emailError.innerHTML = "";
    }
    
    if( !password.value ){
        message.push("<p class='alert'>Ingresa tu password </p>")
        passwordError.innerHTML = "<p class='alert'>Ingresa tu password</p>";    
    }else{
        passwordError.innerHTML = "";
    }
    //event.preventDefault();
    if(message.length>0){
        event.preventDefault();
    }else{
        // if (localStorage.getItem("users")) {
        //     const users = JSON.parse(localStorage.getItem("users"));

        //     const emailFound = users.filter( value => value.email == email.value );

        //     if( emailFound.length > 0 ){
        //         emailError.innerHTML = "";

        //         //const passwordFound = email.filter( value => value.password == password.value );

        //         if( password.value == emailFound[0].password){
        //             passwordError.innerHTML = "";
        //             if(emailFound[0].id==0){
        //               localStorage.setItem("admin","1");
        //             }
        //             localStorage.setItem("login","1");
        //         }else{
        //             event.preventDefault();
        //             passwordError.innerHTML ="<p class='alert'>Password no válido</p>";    
        //         }
        //     }else{
        //         event.preventDefault();
        //         emailError.innerHTML = "<p class='alert'>No existe ese email </p>";
        //     }
        // }else{
        //     event.preventDefault();
        //     emailError.innerHTML = "<p class='alert'>No existe ese email </p>";
        // }
        event.preventDefault();

        const respuesta = (await postUser({email:email.value,password:password.value}));
   
        if(respuesta>=400&&respuesta<=499){

          emailError.innerHTML="";
          passwordError.innerHTML ="<p class='alert'>Password no válido</p>";    

        }
        
        if(respuesta>=500&&respuesta<=599){

          emailError.innerHTML = "<p class='alert'>No existe ese email </p>";    
     
        }

        if(respuesta>=200&&respuesta<=299){
          form.submit();
          emailError.innerHTML="";
          passwordError.innerHTML="";

          localStorage.setItem("login","1");
          localStorage.setItem("admin","1");

        }
    }
});



    //   for (let [key, value] of response.headers) {
    //     console.log(`${key} = ${value}`);
    //   }

    //   const jwt = await response.json();
    //   console.log(jwt);
    //   jwt != null && localStorage.setItem("token", jwt.token);
    // };
/* 
const express = require("express");
const jwt = require("jsonwebtoken");
const bodyParser = require("body-parser");
var cors = require('cors');

const app = express();
const secretKey = "token";


app.use(bodyParser.json());
app.use(cors());
app.set('port', 3000);


app.post("/login", (req, res) => {
    const { userfullname, password } = req.body;

    const user = users.find(u => u.useremail === useremail && u.password === password);

    if (!user) {
      return res.status(401).json({ message: 'Credenciales inválidas' });
    }

    const token = jwt.sign({ userId: user.id }, secretKey, { expiresIn: '1h' });
    res.json({ token });

});

function verifyToken(req, res, next) {
    const token = req.headers['authorization'];
  
    if (!token) {
      return res.status(401).json({ message: 'Token no proporcionado' });
    }
  
    jwt.verify(token, secretKey, (err, decoded) => {
      if (err) {
        return res.status(401).json({ message: 'Token inválido' });
      }
  
      req.userId = decoded.userId;
      next();
    });
  }

  
app.listen(app.get('port'), () => {
    console.log(`Servidor escuchando en el puerto ${app.get('port')}`);
  });

  function authenticateUser(userfullname, password) {
    const user = users.find(u => u.userfullname === userfullname && u.password === password);
    return user;
  } */








  // fetch("http://localhost:8080/api/user/2", { mode: 'no-cors' } { mode: 'no-cors' })
  // .then((response) => {
  //   if(!response.ok){
  //       throw new Error ("Error al obtener usuario");
  //   }
  //   return response.json();
  // })
  // .then((userData) => {
  //   const email = userData.email;
  //   const password = userData.password;

  //   console.log(email);
  //   console.log(password);


// const formEmail = document.getElementById("correo").value;
//   const formPassword = document.getElementById("contraseña").value;



