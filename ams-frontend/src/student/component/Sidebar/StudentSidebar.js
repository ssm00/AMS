/*eslint-disable*/
import React from "react";
import { Link } from "react-router-dom";
import {signout} from "../../../service/ApiService";

export default function Sidebar() {
  const [collapseShow, setCollapseShow] = React.useState("hidden");
  return (
    <>
      <nav className="md:left-0 md:block md:fixed md:top-0 md:bottom-0 md:overflow-y-auto md:flex-row md:flex-nowrap md:overflow-hidden shadow-xl bg-white flex flex-wrap items-center justify-between relative md:w-64 z-10 py-4 px-6">
        <div className="md:flex-col md:items-stretch md:min-h-full md:flex-nowrap px-0 flex flex-wrap items-center justify-between w-full mx-auto">
          {/* Toggler */}
          <button
            className="cursor-pointer text-black opacity-50 md:hidden px-3 py-1 text-xl leading-none bg-transparent rounded border border-solid border-transparent"
            type="button"
            onClick={() => {
              setCollapseShow("bg-white m-2 py-3 px-6");
            }}
          >
            <i className="fas fa-bars"></i>
          </button>
          {/* Brand */}
          <button
            className="md:block text-left md:pb-2 text-blueGray-600 mr-0 inline-block whitespace-nowrap text-sm uppercase font-bold p-4 px-0"
            onClick={() => signout()}
          >
            Log Out
          </button>
          {/* Collapse */}
          <div
            className={
              "md:flex md:flex-col md:items-stretch md:opacity-100 md:relative md:mt-4 md:shadow-none shadow absolute top-0 left-0 right-0 z-40 overflow-y-auto overflow-x-hidden h-auto items-center flex-1 rounded " +
              collapseShow
            }
          >
            {/* Divider */}
            <hr className="my-4 md:min-w-full" />
            {/* Heading */}
            <h6 className="md:min-w-full text-blueGray-500 text-xs uppercase font-bold block pt-1 pb-4 no-underline">
              Menu
            </h6>
            {/* Navigation */}

            <ul className="md:flex-col md:min-w-full flex flex-col list-none">
              <li className="items-center">
                <Link
                  className={
                    "text-xs uppercase py-3 font-bold block " +
                    (window.location.href.indexOf("/student/english/main") !== -1
                      ? "text-lightBlue-500 hover:text-lightBlue-600"
                      : "text-blueGray-700 hover:text-blueGray-500")
                  }
                  to="/student/english/main"
                >
                  <i
                    className={
                      "fas fa-tv mr-2 text-sm " +
                      (window.location.href.indexOf("/student/english/main") !== -1
                        ? "opacity-75"
                        : "text-blueGray-300")
                    }
                  ></i>{" "}
                  영어
                </Link>
              </li>

              <li className="items-center">
                <Link
                  className={
                    "text-xs uppercase py-3 font-bold block " +
                    (window.location.href.indexOf("/student/english/answer") !== -1
                      ? "text-lightBlue-500 hover:text-lightBlue-600"
                      : "text-blueGray-700 hover:text-blueGray-500")
                  }
                  to="/student/english/answer"
                >
                  <i
                    className={
                      "fas fa-tools mr-2 text-sm " +
                      (window.location.href.indexOf("/student/english/answer") !== -1
                        ? "opacity-75"
                        : "text-blueGray-300")
                    }
                  ></i>{" "}
                  영어 정답 입력
                </Link>
              </li>

              <li className="items-center">
                <Link
                  className={
                    "text-xs uppercase py-3 font-bold block " +
                    (window.location.href.indexOf("/student/math/main") !== -1
                      ? "text-lightBlue-500 hover:text-lightBlue-600"
                      : "text-blueGray-700 hover:text-blueGray-500")
                  }
                  to="/student/math/main"
                >
                  <i
                    className={
                      "fas fa-tv mr-2 text-sm " +
                      (window.location.href.indexOf("/student/math/main") !== -1
                        ? "opacity-75"
                        : "text-blueGray-300")
                    }
                  ></i>{" "}
                  수학
                </Link>
              </li>

              <li className="items-center">
                <Link
                  className={
                    "text-xs uppercase py-3 font-bold block " +
                    (window.location.href.indexOf("/student/math/answer") !== -1
                      ? "text-lightBlue-500 hover:text-lightBlue-600"
                      : "text-blueGray-700 hover:text-blueGray-500")
                  }
                  to="/student/math/answer"
                >
                  <i
                    className={
                      "fas fa-tools mr-2 text-sm " +
                      (window.location.href.indexOf("/student/math/answer") !== -1
                        ? "opacity-75"
                        : "text-blueGray-300")
                    }
                  ></i>{" "}
                  수학 정답 입력
                </Link>
              </li>

            </ul>
          </div>
        </div>
      </nav>
    </>
  );
}
