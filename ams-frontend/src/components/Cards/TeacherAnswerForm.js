import React from "react";
import PropTypes from "prop-types";
import AnswerTr from "./AnswerTr";
// components

export default function TeacherAnswerForm({ color }){
  function submitAnswer() {
    let radio = document.getElementsByName("radio1");
    radio.forEach((element) => {
      if (element.checked) {
        console.log(element.value);
      }
    });
  }

  function makeTr(quantity) {
    for(let i = 1; i <= quantity; i++){
      return <AnswerTr key={i} number={i}/>
    }
  }

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
                <th
                    className={
                      "px-6 align-middle border border-solid border-blueGray-200 py-3 text-xs uppercase whitespace-nowrap font-semibold text-center bg-blueGray-50 text-blueGray-500 border-blueGray-100 w-40"
                    }
                >
                  배점
                </th>
              </tr>
              </thead>
              <tbody>
              {makeTr(11)}
              </tbody>
            </table>
          </div>
        </div>
      </>
  );

}

TeacherAnswerForm.defaultProps = {
  color: "light",
};

TeacherAnswerForm.propTypes = {
  color: PropTypes.oneOf(["light", "dark"]),
};
