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
        cell7.innerHTML = ticket.resolverId;
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
  }
}

async function addRequestFunc(){

    console.log("request");

    let a = document.getElementById("amount").value;
    let t = document.getElementById("type").value;
    let d = document.getElementById("description").value;

    let info = {
        amount:a,
        type:t,
        description:d
    };

  

    let response = await fetch(url+'request', {
    method:"POST",
    body: JSON.stringify(info),
    credentials:"include"
  });

  if(response.status===200){
    let data = await response.json(); //This is the res body

  }else{
    document.getElementById("response-box").innerText="Failed Request";

  }
}