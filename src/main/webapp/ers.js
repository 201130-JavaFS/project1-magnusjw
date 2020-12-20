const url = 'http://localhost:8080/project-1/';

//Login Page JS
document.getElementById("loginBtn").addEventListener('click', loginFunc);

async function loginFunc(){
  let usern = document.getElementById("username").value;
  let userp = document.getElementById("password").value;

  let user = {
    username:usern,
    password:userp
  };
  
  let resp = await fetch(url+'login', {
    method:"POST",
    body: JSON.stringify(user),
    credentials:"include"
    //credentials:include will ensure that the cookie is captured, future fetch requests
    //will also require this value in order to send the cookie back.
  });

  if(resp.status===200){
    location.replace("manager.html");
  }else{
    document.getElementById("login-row").innerText="Login Failed!";
  }
}

//Manger Page JS
document.getElementById("pendingBtn").addEventListener('click', pendingTicketsFunc);
document.getElementById("everyBtn").addEventListener('click', everyTicketFunc);
document.getElementById("approveBtn").addEventListener('click', approveFunc);
document.getElementById("rejectBtn").addEventListener('click', rejectFunc);


async function pendingTicketsFunc(){
  let resp = await fetch(url+'login', {
    method:"GET",
    body: JSON.stringify(), //Would I manage my current user id in javascript??
    credentials:"include"
  });
}
async function everyTicketFunc(){
  let resp = await fetch(url+'login', {
    method:"GET",
    body: JSON.stringify(), 
    credentials:"include"
  });
}
async function approveFunc(){

}
async function rejectFunc(){
  
}

//Employee Page JS
document.getElementById("pendingBtn").addEventListener('click', pendingTickets);
document.getElementById("pendingBtn").addEventListener('click', pendingTickets);

