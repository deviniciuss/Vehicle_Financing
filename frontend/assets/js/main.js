let typeFinancing = "";
let months = "";
let vehiclePrice = "";
let nameUser = "";
let emailUser = "";

/*This function will only calculate the Installment's value */
function calculateFinancing(){
  months = document.getElementById("months").value;
  


  vehiclePrice = document.getElementById("vehicle-price").value;
  verifyCheckBox();
  if(typeFinancing === "internal" & months > 48){
   return alertify.error("Internal financing only allows for a maximum of 48 months.");
  } 
  if(typeFinancing === "external" && months > 60){
    return alertify.error("External financing only allows for a maximum of 60 months.");
  } 

  obj = {
    "typeFinancing": `${typeFinancing}`,
    "months": `${months}`,
    "vehiclePrice": `${vehiclePrice}`,
    "user": {
        "name": "Only for test",
        "email": "testing@gamil.com"
    }
  };

  
  fetch('http://localhost:8080/api/vehicle-financing/calculate', {
    method: 'POST',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(obj)
  }).then(res => res.json())
     .then(res => {
    document.getElementById("result-input").setAttribute("placeholder", "€"+ res) ;
  })

}
document.getElementById("btn-calculate").addEventListener("click", calculateFinancing);


/*This function will only verify which type o financing was choose */
function verifyCheckBox(){
  if(document.getElementById('intern-btn').checked){
    typeFinancing = "internal";
  }
  else{
    typeFinancing = "external";
  }
}

/*This function will only verify which type o financing was choose */
function  addFinancing() {
  months = document.getElementById("months").value;
  vehiclePrice = document.getElementById("vehicle-price").value;
  verifyCheckBox();
  nameUser = document.getElementById("name").value;
  emailUser = document.getElementById("email").value;

  obj = {
    "typeFinancing": `${typeFinancing}`,
    "months": `${months}`,
    "vehiclePrice": `${vehiclePrice}`,
    "user": {
        "name": `${nameUser}`,
        "email": `${emailUser}`
    }
  };

  fetch("http://localhost:8080/api/vehicle-financing/add", {    
    method: "POST",
    headers: { 'Content-type': 'application/json' },
    body: JSON.stringify(obj)

  });
  window.location.reload();
  
   
};

document.getElementById("btn-add").addEventListener("click", addFinancing);
  
