import React from "react";

export default function TeacherExamNumStat(props) {
  return (
    <>
      <div className="relative flex flex-col min-w-0 break-words bg-white rounded mb-6 xl:mb-0 shadow-lg">
        <div className="flex-auto p-4">
          <div className="flex flex-wrap">
            <div className="relative w-full pr-4 max-w-full flex-grow flex-1 text-center">
              <span className="font-semibold text-xl text-blueGray-700">
                {props.examNumber}회차
              </span>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}