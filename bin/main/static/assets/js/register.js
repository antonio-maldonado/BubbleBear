
if(localStorage.getItem("login")){
    location.assign("/");
}
//solicitud Post
async function postUser (users) {
    const url = 'http://localhost:8080/api/user'
    try{
        const response = await fetch (url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(users)
        });
        console.log(response.status);
        return response.status;

    } catch (error){
        console.warn(error);
    }
};

class User {
    constructor(fullname, phone_number, email, password, birthday) {
        //this.id = this.calculateID();
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = 0;
    }

    calculateID() {
        if (localStorage.getItem("users")) {
            let users = localStorage.getItem("users");
            users = JSON.parse(users);
            return users.length;
        } else {
            return 0;
        }
    }

    loadDataLocalStorage() {
        let users;
        if (localStorage.getItem("users")) {
            users = JSON.parse(localStorage.getItem("users"));
            users.push(this);
        } else {
            users = [this];
        }
        localStorage.setItem("users", JSON.stringify(users));
    }

    emailExists() {
        if (!localStorage.getItem("users")) return false;
        const users = JSON.parse(localStorage.getItem("users"));
        const userFound = users.filter((value) => value.email == this.email);
        if (userFound.length > 0) {
            return true;
        } else {
            return false;
        }
    }
};

const btn = document.getElementById("form");
btn.addEventListener("submit", async function (e) {


    const name = document.getElementById("full-name");
    const email = document.getElementById("mail");
    const password = document.getElementById("password");
    const passwordConfirmation = document.getElementById("password-confirmation")
    const birthday = document.getElementById("date");

    const lettersRegExp = /^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/;
    const nRegExp = /^\d{10,14}$/;
    const passwordExp = /^(?=.*[0-9])(?=.*[A-Z]).{8,10}$/;
    const emailRegExp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let message = [];

    if (!name.value) {
        const errorMessage = document.getElementById('error-name');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa un nombre</p>";
        message.push("error-name");
    } else if (!lettersRegExp.test(name.value)) {
        const errorMessage = document.getElementById('error-name');
        errorMessage.innerHTML = "<p class='alert mt-3'>El minimo de caracteres de:</p>";
        message.push("error-name");
    } else {
        const errorMessage = document.getElementById('error-name');
        errorMessage.innerHTML = "";
    }

    if (!email.value) {
        const errorMessage = document.getElementById('error-email');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa tu email</p>";
        message.push("error-email");
    } else if (!emailRegExp.test(email.value)) {
        const errorMessage = document.getElementById('error-email');
        errorMessage.innerHTML = "<p class='alert mt-3'>Recuerda que debe contener un @</p>";
        message.push("error-email");
    } else {
        const errorMessage = document.getElementById('error-email');
        errorMessage.innerHTML = "";
    }

    if (!password.value) {
        const errorMessage = document.getElementById('error-password');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa la contraseña</p>";
        message.push("error-password");
    } else if (!passwordExp.test(password.value)) {
        const errorMessage = document.getElementById('error-password');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa una contraseña de al menos longitud 8 y máximo 10 con letras y números</p>";
        message.push("error-password");
    } else {
        const errorMessage = document.getElementById('error-password');
        errorMessage.innerHTML = "";
    }

    if (!passwordConfirmation.value) {
        const errorMessage = document.getElementById('error-password-confirmation');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa la contraseña</p>";
        message.push("error-password-confirmation");
    } else if (password.value != passwordConfirmation.value) {
        const errorMessage = document.getElementById('error-password-confirmation');
        errorMessage.innerHTML = "<p class='alert mt-3'>No coinciden</p>";
        message.push("error-password-confirmation");
    } else {
        const errorMessage = document.getElementById('error-password-confirmation');
        errorMessage.innerHTML = "";
    }

    if (!phone.value) {
        const errorMessage = document.getElementById('error-phone');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa tu numero de telefono</p>";
        message.push("error-phone");
    } else if (phone.value.length!=10) { 
         const errorMessage = document.getElementById('error-phone');
        errorMessage.innerHTML = "<p class='alert mt-3'>El número debe de ser de 10 dígitos</p>";
        message.push("error-phone");
    }else if (!nRegExp.test(phone.value)) {
        const errorMessage = document.getElementById('error-phone');
        errorMessage.innerHTML = "<p class='alert mt-3'>Opps, algo anda mal, solo debe contener 10 dígitos</p>";
        message.push("error-phone");
    } else {
        const errorMessage = document.getElementById('error-phone');
        errorMessage.innerHTML = "";
    }

    if (!birthday.value) {
        const errorMessage = document.getElementById('error-date');
        errorMessage.innerHTML = "<p class='alert mt-3'>Ingresa una fecha</p>";
        message.push("error-date");
    }

    e.preventDefault();
    if (message.length > 0) { 
        e.preventDefault(); 
    } else { 
     
        const user = new User(name.value, phone.value, email.value, password.value, birthday.value);
        //  if (user.emailExists()) { 
        //     e.preventDefault();
        //     const errorMessage = document.getElementById("error-email");
        //     errorMessage.innerHTML = "<p class='alert mt-3'>Ese email ya existe, intenta con otro</p>";
        // } else {
        //     const errorMessage = document.getElementById("error-email");
        //     errorMessage.innerHTML = "";
        //     user.loadDataLocalStorage();
        // } 
        // console.log(user);
       
        const respuesta = await postUser(user);
        //const respuesta = 400;
        if(respuesta>=400&&respuesta<=499){
            
          emailError.innerHTML="";
          passwordError.innerHTML ="<p class='alert'>Password no válido</p>";    

        }
        
        if(respuesta>=500&&respuesta<=599){
            const errorMessage = document.getElementById('error-email');
            errorMessage.innerHTML = "<p class='alert mt-3'>Ese email ya existe</p>";
          emailError.innerHTML = "<p class='alert'>No existe ese email </p>";    
          console.log("fue 500")
        }

        if(respuesta>=200&&respuesta<=299){
          btn.submit();
            
        }
    }
});

/* Solicitud get */

/* 
const urlUsers = "http://localhost:8080/api/user"
async function getUsers ( url ){
    try{
        const responseJSON = await fetch( url );
        console.log(responseJSON.status);
        const response = await responseJSON.json();
        console.log( response );
    }
    catch( error ){
       console.error(error);
    }
           
   };
   getUsers(urlUsers);





   //solicitud Post
async function postUser (users) {
    const url = 'http://localhost:8080/api/user'
    try{
        const response = await fetch (url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(users)
            
        });

    } catch (error){
        console.warn(error);
    }
};

const urlSignup = "http://localhost:8080/api/user/signup";
const userData = {
    Name: 'Allan Espitia',
    email: 'espitiaallan@gmail.com',
    password: 'dia123',
    passwordConfirmation: 'dia123',
    birthday: '30-10-1997',
    phone_number: '3333947714'
};


 */