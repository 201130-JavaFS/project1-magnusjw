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

  localStorage["username"] = usern;
  
  let response = await fetch(url+'login', {
    method:"POST",
    body: JSON.stringify(user),
    credentials:"include"
    //credentials:include will ensure that the cookie is captured, future fetch requests
    //will also require this value in order to send the cookie back.
  });

  if(response.status===200){  
    let data = await response.json(); //This is the res body

    console.log(data);

    if(data == 1){
      location.replace("employee.html");
    }

    if(data == 2)
      location.replace("manager.html");
      
  }else{
    document.getElementById("login-row").innerText="Login Failed!";
  }
}