import React, {useEffect} from "react";
import PropTypes from "prop-types";
import TeacherAnswerTr from "./TeacherAnswerTr";
import {call} from "../../service/ApiService";
import StudentAnswerTr from "./StudentAnswerTr";
// components

export default function StudentEngAnswerForm({ color , examNumber }){
  const tr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45];
  let answer = "";
  function SubmitStudentEngAnswer(e) {
      e.preventDefault();
      for(let i=1; i<=tr.length; i++){
          let radioElement = document.getElementsByName("radio" + i);
          for (let j = 0; j < radioElement.length; j++) {
              if (radioElement[j].checked === true) {
                  answer += radioElement[j].value + ",";
                  break;
              }
              if (j === radioElement.length - 1 && radioElement[j].checked === false) {
                  alert(i + "번 정답이 입력되지 않았습니다.");
                  answer = "";
                  return;
              }
          }
      };
      call("/users/answers", "POST", {
          "examNumber": examNumber,
          "examSubject": "ENGLISH",
          "examAnswer": answer,
      }).then((response) => {
          alert(response.result);
      }).catch((error) => {
          alert(error);
      });
    };

  return (
      <>
        <div
            className={
                "relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded " +
                (color === "light" ? "bg-white" : "bg-lightBlue-900 text-white")
            }
        >
          <div className="rounded-t mb-0 px-4 py-3 border-0">
            <div className="flex flex-wrap items-center">
              <div className="relative w-full px-4 max-w-full flex-grow flex-1">
                <h3
                    className={
                        "font-semibold text-lg " +
                        (color === "light" ? "text-blueGray-700" : "text-white")
                    }
                >
                  시험 정답 입력
                </h3>
              </div>
            </div>
          </div>
          <div className="block w-full overflow-x-auto">
            {/* Projects table */}
            <table className="items-center w-full bg-transparent">
              <thead>
              <tr>
                <th
                    className={
                        "px-6 align-middle border border-solid border-blueGray-200 py-3 text-xs uppercase whitespace-nowrap font-semibold text-center " +
                        (color === "light"
                            ? "bg-blueGray-50 text-blueGray-500 border-blueGray-100 w-3.5"
                            : "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700")
                    }
                >
                  문제 번호
                </th>
                <th
                    className={
                        "px-6 align-middle border border-solid border-blueGray-200 py-3 text-xs uppercase whitespace-nowrap font-semibold text-center " +
                        (color === "light"
                            ? "bg-blueGray-50 text-blueGray-500 border-blueGray-100"
                            : "bg-lightBlue-800 text-lightBlue-300 border-lightBlue-700")
                    }
                >
                  정답 선택
                </th>
              </tr>
              </thead>
              <tbody>
              {tr.map((tr) => {
                return <StudentAnswerTr key={tr} number={tr}/>
              })
              }
              <tr>
                  <td className="border-t-0 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4 text-left flex items-center">
                  <span
                      className={
                          "ml-3 font-bold blueGray"
                      }
                  >
                    제출하기
                  </span>
                  </td>
                  <td>
                      <button
                          className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                          onClick={SubmitStudentEngAnswer}
                      >
                          제출
                      </button>
                  </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </>
  );

}

StudentEngAnswerForm.defaultProps = {
  color: "light",
};

StudentEngAnswerForm.propTypes = {
  color: PropTypes.oneOf(["light", "dark"]),
};
