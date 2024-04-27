
function requestHeader(body) {
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: body,
    };
    return requestOptions;
}

function requestJson(body) {
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: body,
    };
    return requestOptions;
}

export {requestHeader, requestJson};