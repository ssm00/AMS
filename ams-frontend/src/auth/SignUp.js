import React from "react";
import Navbar from "../components/Navbars/AuthNavbar";
import FooterSmall from "../components/Footers/FooterSmall";
import {signup} from "../service/ApiService";
export default function SignUp() {
  const [logInId, setLogInId] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [name, setName] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [schoolType, setSchoolType] = React.useState("HIGH");
  const [className, setClassName] = React.useState("AP1");
  const [schoolName, setSchoolName] = React.useState("대원고");
  const [grade, setGrade] = React.useState("1");

  const signUpSubmit = (event) => {
    event.preventDefault();
    signup({ logInId: logInId, email: email, name: name, password: password, schoolType: schoolType, className: className, type:"S", schoolName: schoolName,grade: Number(grade) });
  }
  return (
    <>
      <Navbar transparent />
      <main>
        <section className="relative w-full h-full py-40 min-h-screen">
          <div
              className="absolute top-0 w-full h-full bg-blueGray-800 bg-no-repeat bg-full"
              style={{
                backgroundImage:
                    "url(" + require("../assets/img/register_bg_2.png").default + ")",
              }}
          ></div>
              <div className="container mx-auto px-4 h-full">
                <div className="flex content-center items-center justify-center h-full">
                  <div className="w-full lg:w-6/12 px-4">
                    <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-blueGray-200 border-0">
                      <div className="rounded-t mb-0 px-6 py-6">
                        <div className="text-center mb-3">
                          <h6 className="text-blueGray-500 text-sm font-bold">
                            소셜 로그인
                          </h6>
                        </div>
                        <div className="btn-wrapper text-center">
                          <button
                            className="bg-white active:bg-blueGray-50 text-blueGray-700 font-normal px-4 py-2 rounded outline-none focus:outline-none mr-2 mb-1 uppercase shadow hover:shadow-md inline-flex items-center font-bold text-xs ease-linear transition-all duration-150"
                            type="button"
                          >
                            <img
                              alt="..."
                              className="w-5 mr-1"
                              src={require("../assets/img/github.svg").default}
                            />
                            Github
                          </button>
                          <button
                            className="bg-white active:bg-blueGray-50 text-blueGray-700 font-normal px-4 py-2 rounded outline-none focus:outline-none mr-1 mb-1 uppercase shadow hover:shadow-md inline-flex items-center font-bold text-xs ease-linear transition-all duration-150"
                            type="button"
                          >
                            <img
                              alt="..."
                              className="w-5 mr-1"
                              src={require("../assets/img/google.svg").default}
                            />
                            Google
                          </button>
                        </div>
                        <hr className="mt-6 border-b-1 border-blueGray-300" />
                      </div>
                      <div className="flex-auto px-4 lg:px-10 py-10 pt-0">
                        <div className="text-blueGray-400 text-center mb-3 font-bold">
                          <small>Or sign up with credentials</small>
                        </div>
                        <form onSubmit={signUpSubmit}>
                          <div className="relative w-full mb-3">
                            <label
                              className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                              htmlFor="grid-password"
                            >
                              아이디
                            </label>
                            <input
                              type="text"
                              className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                              placeholder="아이디"
                              value={logInId}
                              onChange={(e) => setLogInId(e.target.value)}
                            />
                          </div>

                          <div className="relative w-full mb-3">
                            <label
                              className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                              htmlFor="grid-password"
                            >
                              이메일
                            </label>
                            <input
                              type="email"
                              className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                              placeholder="이메일"
                              value={email}
                              onChange={(e) => setEmail(e.target.value)}
                            />
                          </div>

                          <div className="relative w-full mb-3">
                            <label
                              className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                              htmlFor="grid-password"
                            >
                              이름
                            </label>
                            <input
                              type="text"
                              className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                              placeholder="이름"
                              value={name}
                              onChange={(e) => setName(e.target.value)}
                            />
                          </div>
                          <div className="relative w-full mb-3">
                            <label
                                className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                                htmlFor="grid-password"
                            >
                              비밀번호
                            </label>
                            <input
                                type="password"
                                className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                                placeholder="비밀번호"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                          </div>
                          <div className="relative w-full mb-3">
                            <label
                                className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                                htmlFor="grid-password"
                            >
                              학교 유형
                            </label>
                            <select
                                className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                                placeholder="학교 유형"
                                onChange={event => setSchoolType(event.target.value)}
                                >
                                <option key="HIGH" value="HIGH" >고등학생</option>
                                <option key="MIDDLE" value="MIDDLE" >중학생</option>
                            </select>
                          </div>
                          <div className="relative w-full mb-3">
                            <label
                                className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                                htmlFor="grid-password"
                            >
                              학교
                            </label>
                            <select
                                className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                                placeholder="학교"
                                onChange={event => setSchoolName(event.target.value)}
                            >
                              <option key="1" value="대원고등학교" >대원고</option>
                              <option key="2" value="대원여자고" >대원여자고</option>
                              <option key="3" value="건대부고" >건대부고</option>
                              <option key="4" value="동대부여고" >동대부여고</option>
                            </select>
                          </div>
                          <div className="relative w-full mb-3">
                            <label
                                className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                                htmlFor="grid-password"
                            >
                              반선택
                            </label>
                            <select
                                className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                                placeholder="반선택"
                                onChange={event => setClassName(event.target.value)}
                            >
                              <option key="AP1" value="AP1" >AP1</option>
                              <option key="AP2" value="AP2" >AP2</option>
                            </select>
                          </div>
                          <div className="relative w-full mb-3">
                            <label
                                className="block uppercase text-blueGray-600 text-xs font-bold mb-2"
                                htmlFor="grid-password"
                            >
                              학년
                            </label>
                            <select
                                className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                                placeholder="학년"
                                onChange={event => setGrade(event.target.value)}
                            >
                              <option key="1" value="1" >1</option>
                              <option key="2" value="2" >2</option>
                              <option key="3" value="3" >3</option>
                            </select>
                          </div>
                          <div className="text-center mt-6">
                            <button
                              className="bg-blueGray-800 text-white active:bg-blueGray-600 text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full ease-linear transition-all duration-150"
                              type="submit"
                            >
                              확인
                            </button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
          <FooterSmall absolute />
        </section>
      </main>
    </>
  );
}
