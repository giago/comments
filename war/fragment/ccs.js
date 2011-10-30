<script type="text/javascript">
//leave this because this file is included with jsp

var SERVICES_URL_PREFIX = "";

/*
 * =======================================================================
 *   Ajax calls
 * =======================================================================
 */
function getCommentsForUrl(url) {
	callServices(getServiceUrl("getComments", url), 
		function(response) {
			if(response != null && response.data != null) {
				showComments(response.data);
			} else {
				setMessages("Sorry no data for this url.");
			}					
		});
}

function sendCommentForUrl(url, comment) {
	callServices(getServiceUrl("addComment", url, comment), 
		function(response) {
			showInfoMessageWithTimeout("Thanks for the comment!");
			getCommentsForUrl(url);
		});
}

function sendVoteForComment(url, id, vote) {
	callServices(getServiceUrlForVote("voteComment", id, vote), 
		function(response) {
			showInfoMessageWithTimeout("Thanks for voting!");
			getCommentsForUrl(url);
		});
}

function getMostVotedUrl() {
	callServices(getServiceUrl("getMostCommentedUrl"), 
		function(response) {
			showComments(response.data);
		});
}

function getMostVotedHost() {
	callServices(getServiceUrl("getMostCommentedHost"), 
		function(response) {
			showComments(response.data);
		});
}

/*
 * =======================================================================
 *   Ajax generic call and response manager
 * =======================================================================
 */
function callServices(url, handler) {
	startCall();
	try {
		var xhr = new XMLHttpRequest();
		var abortTimerId = window.setTimeout(function() { xhr.abort(); }, 180000);
		  
		xhr.onreadystatechange = function() {
		  	if (xhr.readyState != 4) { return; }
			handleSuccess(xhr.responseText, handler);
		}; 
		xhr.open("POST", url , true);
		xhr.send(null);
	} catch (e) {
	    window.clearTimeout(abortTimerId);
	}
}

function handleSuccess(response, handler) {
	if(response == null && response == ""){
		return;
	}
	try {
		var resp;
		if(window.JSON){
			resp = window.JSON.parse(response);
		} else {
			resp = evalJson(response);
		}
		handler(resp);
		endCall();
	} catch (e) {
		endCall();
		setMessages("Sorry there was an exception in the server call, please try agin.");
	}
}

function evalJson(str) { 
	if (str === "") str = '""'; 
		eval("var p=" + str + ";"); 
	return p; 
}

/*
 * =======================================================================
 *   Call status manager
 * =======================================================================
 */
function startCall() {
	setMessages("Loading...");
	changeStatusOfServiceCallerElement(true);
}

function changeStatusOfServiceCallerElement(disabled) {
	var searchButton = getSearchButtonElement();
	if(searchButton) {
		searchButton.disabled = disabled;
	}
	var commentButton = getCommentButtonElement();
	if(commentButton) {
		commentButton.disabled = disabled;
	}
} 

function endCall() {
	setMessages("");
	changeStatusOfServiceCallerElement(false);
}

/*
 * =======================================================================
 *   Url generation
 * =======================================================================
 */
function getServiceUrl(action) {
	return getServiceUrlWithId(action, null);
}

function getServiceUrlWithId(action, id) {
	return getServiceUrl(action, null, null, id);
}

function getServiceUrl(action, url, comment, id) {
	return SERVICES_URL_PREFIX + "crappyCommentsServices?"
		+ getActionParameter(action) 
		+ getCommentParameter(comment) 
		+ getUrlParameter(url)
		+ getIdParameter(id);
}

function getServiceUrlForVote(action, id, vote) {
	return getServiceUrlWithId(action, id) + getVoteParameter(vote); 
}

function getCommentParameter(comment) { return getParameterForUrl("comment", comment);}
function getUrlParameter(url) { return getParameterForUrl("url", url);}
function getActionParameter(action) { return getParameterForUrl("action", action); }
function getIdParameter(id) { return getParameterForUrl("id", id); }
function getVoteParameter(vote) { return getParameterForUrl("vote", vote); }

function getParameterForUrl(name, value) {
	if(value == null) {
		return "";
	} 
	return "&" + name + "=" + value;
}


/**
 * Get the encoded url parameter from the url
 * TODO verify if is changing the values of url
 * @param name
 * @return
 */
function getUrlParameterName(name) {
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var regexS = "[\\?&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec( window.location.href );
	if( results == null )
		return "";
	else
		return results[1];
}

/*
 * =======================================================================
 *   UI manipulation
 * =======================================================================
 */
function showComments(comments) {
	clear();
	var i = 0;
	for(i in comments) {
		showComment(comments[i]);
		i++;
	}
	if(i == 0) {
		showNoResultMessage();
	}
}

function showNoResultMessage() {
	var node = document.createElement("div");
	node.setAttribute("class", "commentContainer");
	node.setAttribute("align", "left");
	node.innerHTML	= 
			"<div class=\"commentTitle\">There are no comment for this url, plese fill free to add one!</div>";
	getCommentsElement().appendChild(node);
}

function showComment(result) {
	var node = document.createElement("div");
	node.setAttribute("class", "commentContainer");
	if(result.url) {
		node.innerHTML	= 
			"<div class=\"commentTitle\">With votes : <span class=\"positive\">+" + result.positiveVotes 
					+ "</span> <span class=\"negative\">-" + result.negativeVotes + "</span>" + 
			"</div>" +
			"<div class=\"commentText\"><a href=\"" + result.url +"\" target=\"_blank\">" + result.url + "</div>";
	} else if(result.host) {
		node.innerHTML	= 
			"<div class=\"commentTitle\">With votes : <span class=\"positive\">+" + result.positiveVotes 
					+ "</span> <span class=\"negative\">-" + result.negativeVotes + "</span>" + 
			"</div>" +
			"<div class=\"commentText\"><a href=\"http://" + result.host +"\" target=\"_blank\">" + result.host + "</div>";
	} else {
		node.innerHTML	= 
			"<div class=\"commentTitle\">" 
				+ result.createdDate + " - votes : <span class=\"positive\">+" + result.positiveVotes 
					+ "</span> <span class=\"negative\">-" + result.negativeVotes + "</span>"
				+ " - " + getVoteButtonHtml(result.id) + 
			"</div>"
			+ "<div class=\"commentText\">" + result.text + "</div>";
	}
	getCommentsElement().appendChild(node);
}

function getVoteButtonHtml(id) {
	return "<a onClick=\"vote('" + id + "', 1);\">like it</a> or " + 
		"<a onClick=\"vote('" + id + "', -1);\">don't like it</a>";
}

function showInfoMessageWithTimeout(msg) {
	setMessages(msg);
	window.setTimeout(function() { setMessages(""); }, 2000);
}

function clear() {
	var node = getCommentsElement();
    if(node.hasChildNodes()) {
        while(node.childNodes.length >= 1 ) {
            node.removeChild(node.firstChild);       
        } 
    }
}

function addCommentToUrl(url) {
	var comment = getNewComment();
	if(comment == null || comment == "") {
		setMessages("Please send only comment with some content, thanks.");
		return;
	}
	if(url == null || url == "") {
		setMessages("Please send only comments for a real url, thanks.");
		return;
	}
	sendCommentForUrl(url, comment);
}


/*
 * =======================================================================
 *   Entry points
 * =======================================================================
 */
function startIframeSearch() { 
	getCommentsForUrl(getUrlFromRequest()); 
}

function init() {
	var url = getUrl();
	if(url == null) {
		url = "http://crappycomments.appspot.com/index.html";
		setUrl(url);
	} 
	getCommentsForUrl(url);
}

function search() { 
	getCommentsForUrl(getUrl()); 
}

function limitTextArea(textArea) {
	if (textArea.value.length >= 160) {
		textArea.value = textArea.value.substring(0, 159);
	}
}

function addCommentWithUrlFromRequest() {
	addCommentToUrl(getUrlFromRequest());
}

function addComment() {
	addCommentToUrl(getUrl());
}

/*
 * =======================================================================
 *   Dom manipulation
 * =======================================================================
 */
function getSearchButtonElement() { return getElement("searchButton"); }
function getCommentButtonElement() { return getElement("commentButton"); }

function setUrl(url) { getUrlElement().innerHTML = url; }
function getUrl() { return getUrlElement().value; }
function getUrlFromRequest() { return getUrlParameterName("url"); }
function getUrlElement() { return getElement("url"); }

function getCommentsElement() { return getElement("comments"); }
function getNewComment() { return getElement("newComment").value; }

function getMessagesElement() { return getElement("messages"); }
function setMessages(msg) { getMessagesElement().innerHTML = msg; }

function getCommentButtonElement() { return getElement("commentButton"); }

function getElement(id) { return document.getElementById(id); }

//leave this because this file is included with jsp
</script>