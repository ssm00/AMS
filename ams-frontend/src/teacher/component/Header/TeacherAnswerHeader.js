import React from "react";

// components


import TeacherEngStat from "./TeacherEngStat";
import TeacherExamNumStat from "./TeacherExamNumStat";

//맨위에 4개
export default function TeacherAnswerHeader(
    props
) {
  let userName = sessionStorage.getItem("userName");
  return (
    <>
      {/* Header */}
      <div className="relative bg-lightBlue-600 md:pt-32 pb-32 pt-12">
        <div className="px-4 md:px-10 mx-auto w-full">
          <div>
            {/* Card stats */}
            <div className="flex flex-wrap">
              <div className="w-full lg:w-4/12 xl:w-4/12 px-4 h-100">
                <TeacherEngStat
                  statTitle={props.statTitle}
                />
              </div>
              <div className="w-full lg:w-4/12 xl:w-4/12 px-4 h-100">
                <TeacherExamNumStat
                    examNumber={props.examNumber}
                />
              </div>
              <div className="w-full lg:w-4/12 xl:w-4/12 px-4">
                <TeacherEngStat
                  statTitle={userName+"님"}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
