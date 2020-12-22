const url = 'http://localhost:8080/project-1/';

//Employee Page JS
document.getElementById("viewBtn").addEventListener('click', viewTicketsFunc);
document.getElementById("requestBtn").addEventListener('click', addRequestFunc);

async function onloadFunc(){
    document.getElementById("user-destination").innerText=localStorage["username"];
}

async function viewTicketsFunc(){

  console.log("view");
  let response = await fetch(url+'view', {
  method:"GET",
  credentials:"include"
  });

  if(response.status===200){
    let data = await response.json(); //This is the res body

    document.getElementById("tbody").innerHTML = "";

    for(let ticket of data){
        console.log(ticket);

        let row = document.createElement("tr");

        let cell = document.createElement("td");
        cell.innerHTML = ticket.id;
        row.appendChild(cell);

        let cell2 = document.createElement("td");
        cell2.innerHTML = ticket.amount;
        row.appendChild(cell2);

        let cell3 = document.createElement("td"); //Needs work
        cell3.innerHTML = ticket.submitted;
        row.appendChild(cell3);

        let cell4 = document.createElement("td"); //Needs work
        cell4.innerHTML = ticket.resolved;
        row.appendChild(cell4);

        let cell5 = document.createElement("td");
        cell5.innerHTML = ticket.description;
        row.appendChild(cell5);

        let cell6 = document.createElement("td");
        cell6.innerHTML = ticket.authorId;
        row.appendChild(cell6);

        let cell7 = document.createElement("td");
        if(ticket.resolverId == 0){
            cell7.innerHTML = "";   
        } else{
            cell7.innerHTML = ticket.resolverId;
        }
        row.appendChild(cell7);

        let cell8 = document.createElement("td");
        if(ticket.statusId == 1){
            cell8.innerHTML = "Pending";
        } else if(ticket.statusId == 2){
            cell8.innerHTML = "Accepted";
        } else {
            cell8.innerHTML = "Rejected";
        }
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        if(ticket.typeId == 1){
            cell9.innerHTML = "Lodging";
        } else if(ticket.typeId == 2){
            cell9.innerHTML = "Travel";
        } else if(ticket.typeId == 3){
            cell9.innerHTML = "Food";
        } else {
            cell9.innerHTML = "Other";
        }
        row.appendChild(cell9);

        document.getElementById("tbody").appendChild(row);
    }
  } else {
        document.getElementById("info").innerText="No Entries";
  }
}

async function addRequestFunc(){

    console.log("request");

    let a = document.getElementById("amount").value;
    let ta = document.getElementById("type").value;
    let d = document.getElementById("description").value;

    document.getElementById("amount").value = "";
    document.getElementById("type").value = "other";
    document.getElementById("description").value = "";

    let t;

    if(ta == "lodging"){
        t = 1;
    } else if(ta == "travel") {
        t = 2;
    } else if(ta == "food") {
        t = 3;
    } else {
        t = 4;
    }

    console.log(t);

    let info = {
        amount:a,
        typeId:t,
        description:d
    };

    let response = await fetch(url+'request', {
    method:"POST",
    body: JSON.stringify(info),
    credentials:"include"
  });

  if(response.status===200){
    document.getElementById("info").innerText = "Submitted";

} else if(response.status===400){
    document.getElementById("info").innerText = "Unexpected Error";

} else if(response.status===401){
    document.getElementById("info").innerText = "Amount cannot be Negative";

} else if(response.status===402){
    document.getElementById("info").innerText = "Amount cannot be Zero";
    
} else if(response.status===403){
    document.getElementById("info").innerText = "Invalid Type";
}
}