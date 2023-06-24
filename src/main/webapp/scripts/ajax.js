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
        handleRequestError(request);
      }
    }
  };

  setTimeout(function() {
    if (request.readyState < 4) {
      request.abort();
    }
  }, 15000);

  if (method.toLowerCase() === "get") {
    sendGetRequest(url, params, request);
  } else {
    sendPostRequest(url, params, request);
  }
}

function handleRequestError(request) {
  let errorMessage = "Problemi nell'esecuzione della richiesta: ";

  if (request.status === 0) {
    errorMessage += "nessuna risposta ricevuta nel tempo limite";
  } else {
    errorMessage += request.statusText;
  }

  alert(errorMessage);
  return null;
}

function sendGetRequest(url, params, request) {
  const fullUrl = params ? url + "?" + params : url;
  request.open("GET", fullUrl, true);
  request.setRequestHeader("Connection", "close");
  request.send(null);
}

function sendPostRequest(url, params, request) {
  if (!params) {
    console.log("Usa GET se non ci sono parametri!");
    return null;
  }

  request.open("POST", url, true);
  request.setRequestHeader("Connection", "close");
  request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  request.send(params);
}




function handleEmail(request){
	let response = request.responseXML.documentElement;
	let result = response.getElementsByTagName("result")[0].firstChild.nodeValue;
    document.getElementById("emailCheckDisponibility").innerHTML = result;
    document.getElementById("emailCheckDisponibility").style.color="red";
    
}

