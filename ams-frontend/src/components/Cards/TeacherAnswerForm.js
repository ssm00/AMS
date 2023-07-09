import React from "react";
import PropTypes from "prop-types";
// components

export default function TeacherAnswerForm({ color }){
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
          <table className="items-center w-full bg-transparent border-collapse">
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
              <tr>
                <td className="border-t-0 px-6 align-middle border border-solid text-xs whitespace-nowrap p-4 text-left flex items-center">
                  <span
                    className={
                      "ml-3 font-bold " +
                      +(color === "light" ? "text-blueGray-600" : "text-white")
                    }
                  >
                    1번
                  </span>
                </td>
                <td className="border-blueGray-100 text-xs uppercase font-semibold text-center border border-solid">
                  <div
                      className="inline-flex rounded-md shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none1 focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
                      role="toolbar"
                  >

                      <button
                          type="button"
                          className="inline-block rounded-l bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-1501 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700"
                      >
                        1
                      </button>

                      <button
                          type="button"
                          className="inline-block bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-150 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700"
                      >
                        2
                      </button>

                      <button
                          type="button"
                          className="inline-block bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-150 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700"
                      >
                        3
                      </button>


                      <button
                          type="button"
                          className="inline-block bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-150 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700"
                      >
                        4
                      </button>


                      <button
                          type="button"
                          className="inline-block rounded-r bg-primary-100 px-7 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-black transition duration-150 ease-in-out hover:bg-success-600 focus:bg-success-600 focus:outline-none focus:ring-0 active:bg-success-700"
                      >
                        5
                      </button>

                  </div>
                </td>
                <td>
                  <input
                      type="text"
                      className="border-0 px-3 py-3 placeholder-blueGray-300 text-blueGray-600 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full ease-linear transition-all duration-150"
                      placeholder="점수 입력"
                      // id= "loginId"
                      // value={logInId}
                      // onChange={(event) => setLogInId(event.target.value)}
                  />
                </td>
              </tr>
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
