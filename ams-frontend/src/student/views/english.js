import React from "react";

// components
import StudentSidebar from "../component/Sidebar/StudentSidebar.js";
import StudentHeaderStats from "../component/Header/StudentHeaderStats.js";
import CardGradeRanking from "../component/Cards/CardGradeRanking.js";
import CardWrongRateRanking from "../component/Cards/CardWrongRateRanking.js";
import CardStudentGrade from "../component/Cards/CardStudentGrade.js";
import CardGradeGraph from "../component/Cards/CardGradeGraph.js";
import CardStudentScoreRanking from "../component/Cards/CardStudentScoreRanking.js";

export default function EnglishMain() {
  return (
    <>
    <StudentSidebar />
    <div className="relative md:ml-64 bg-blueGray-100">
    <StudentHeaderStats />
      <div className="px-4 md:px-10 mx-auto w-full -m-24">
        <div className="flex flex-raw mt-4">
          <div className="w-full xl:w-6/12 px-4">
            <CardGradeRanking
              examNumber={1}
              examSubject={"ENGLISH"}
            />
          </div>
          <div className="w-full xl:w-6/12 px-4">
            <CardWrongRateRanking 
              examNumber={1}
              examSubject={"ENGLISH"}
            />
          </div>
        </div>
        <div className="flex flex-wrap mt-4">
          <div className="w-full xl:w-12/12 mb-12 xl:mb-0 px-4">
            <CardStudentGrade 
              examNumber={1}
              examSubject={"ENGLISH"}
            />
          </div>
        </div>
        <div className="flex flex-raw mt-4">
          <div className="w-full xl:w-8/12 px-4">
            <CardGradeGraph
              examSubject={"ENGLISH"}
            />
          </div>
          <div className="w-full xl:w-4/12 px-4">
            <CardStudentScoreRanking
              examSubject={"ENGLISH"}
            />
          </div>
        </div>
      </div>
      </div>
    </>
  );
}
