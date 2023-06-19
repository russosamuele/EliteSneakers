function tryEmail() {
    let input = document.getElementById('email').value;
    let params = 'email=' + input;
    loadAjaxDoc('../EmailDisponibility', "GET", params, handleEmail);
}

function createXMLHttpRequest() {
	let request;
	try {
		// Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	} catch (e) {
		// past versions of Internet Explorer
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");  
		} catch (e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
	return request;
}

/*function loadAjaxDoc(url, method, params, cFuction) {
	let request = createXMLHttpRequest();
	if(request){
		
		request.onreadystatechange = function() {
			if (this.readyState == 4) {
				if (this.status == 200) {
				    cFuction(this); 
				} else {				
					if(this.status == 0){ // When aborting the request
						alert("Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
					} else { // Any other situation
						alert("Problemi nell'esecuzione della richiesta:\n" + this.statusText);
					}
					return null;
				}
		    }
		};
		
		setTimeout(function () {     // to abort after 15 sec
        	if (request.readyState < 4) {
            	request.abort();
        	}
    	}, 15000); 
		
		if(method.toLowerCase() == "get"){
			if(params){
				request.open("GET", url + "?" + params, true);
			} else {
				request.open("GET", url, true);
			}
			request.setRequestHeader("Connection", "close");
	        request.send(null);
		} else {
			if(params){
				request.open("POST", url, true);
				request.setRequestHeader("Connection", "close");
				request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        	request.send(params);
			} else {
				console.log("Usa GET se non ci sono parametri!");
				return null;
			}
		}
	}
}

*/


function loadAjaxDoc(url, method, params, cFunction) {
  const request = createXMLHttpRequest();
  
  if (!request) {
    return null;
  }
  
  request.onreadystatechange = function() {
    if (request.readyState === 4) {
      if (request.status === 200) {
        cFunction(request);
      } else {
        let errorMessage = "Problemi nell'esecuzione della richiesta: ";
        
        if (request.status === 0) {
          errorMessage += "nessuna risposta ricevuta nel tempo limite";
        } else {
          errorMessage += request.statusText;
        }
        
        alert(errorMessage);
        return null;
      }
    }
  };
  
  setTimeout(function() {
    if (request.readyState < 4) {
      request.abort();
    }
  }, 15000);
  
  if (method.toLowerCase() === "get") {
    const fullUrl = params ? url + "?" + params : url;
    request.open("GET", fullUrl, true);
    request.setRequestHeader("Connection", "close");
    request.send(null);
  } else {
    if (!params) {
      console.log("Usa GET se non ci sono parametri!");
      return null;
    }
    
    request.open("POST", url, true);
    request.setRequestHeader("Connection", "close");
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send(params);
  }
}



function handleEmail(request){
	let response = request.responseXML.documentElement;
	let result = response.getElementsByTagName("result")[0].firstChild.nodeValue;
    document.getElementById("emailCheckDisponibility").innerHTML = result;
    document.getElementById("emailCheckDisponibility").style.color="red";
    
}

