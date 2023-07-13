import React from "react";

// components


import StudentCardStat from "./StudentCardStat.js";
import StudentCardSelect from "./StudentCardSelect.js"

//맨위에 4개
export default function StudentHeaderStats() {
  return (
    <>
      {/* Header */}
      <div className="relative bg-lightBlue-600 md:pt-32 pb-32 pt-12">
        <div className="px-4 md:px-10 mx-auto w-full">
          <div>
            {/* Card stats */}
            <div className="flex flex-wrap">
              <div className="w-full lg:w-4/12 xl:w-4/12 px-4 h-100">
                <StudentCardStat
                  statTitle="English"
                />
              </div>
              <div className="w-full lg:w-4/12 xl:w-4/12 px-4 h-100">
                <StudentCardSelect
                />
              </div>
              <div className="w-full lg:w-4/12 xl:w-4/12 px-4">
                <StudentCardStat
                  statTitle="홍길동님"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
