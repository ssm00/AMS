import { API_BASE_URL } from "../app-config";
const ACCESS_TOKEN = "ACCESS_TOKEN";

export function call(api, method, request) {
    // 헤더 설정
    let headers = new Headers({
        "Content-type": "application/json",
    });
    //token 설정
    const accessToken = sessionStorage.getItem("ACCESS_TOKEN");
    if (accessToken && accessToken !== null) {
        headers.append("Authorization", "Bearer " + accessToken);
    }
    let options = {
        headers: headers,
        url: API_BASE_URL + api,
        method: method,
    };
    //request가 있는경우 body 설정
    if (request) {
        options.body = JSON.stringify(request);
    }
    //첫번째 then의 response는 받을데이터(json) 형태의 깡통 세팅, 두번째 then의 response는 받은데이터임
    return fetch(options.url, options)
        .then((response) =>
            response.json().then((json) => {
                if(json.isSuccess === false){
                    return Promise.reject(json);
                }
                if (!response.ok) {
                    return Promise.reject(json);
                }
                return json;
            })
        )
        .catch((error) => {
            if (error.status === 403) {
                console.log(error, "error ");
                window.location.href = "/login";
            }
            return Promise.reject(error);
        });
}

export function logIn(userDTO) {
    return call("/users/log-in", "POST", userDTO).then((response) => {
        //학생이면 학생페이지로, 선생이면 선생페이지로 이동 추가해야함
        if (response.isSuccess) {
            sessionStorage.setItem(ACCESS_TOKEN, response.result.token);
            sessionStorage.setItem("userName",response.result.userName)
            if (response.result.userType === "T") {
                window.location.href = "/teacher";
            }else if(response.result.userType === "S") {
                window.location.href = "/student";
            }
        }else {
            alert(response.message);
        }
    });
}

export function signout() {
    sessionStorage.clear();
    window.location.href = "/login";
}

export function signup(userDTO) {
    return call("/users/sign-up", "POST", userDTO).then((response) => {
        console.log(response);
        if (response.isSuccess) {
            alert("회원가입이 완료되었습니다.");
            window.location.href = "/login";
        } else {
            alert(response.message);
        }
    });
}
