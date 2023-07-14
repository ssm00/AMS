import React from "react";

// components

import TeacherEngAverageChart from "./component/Cards/TeacherEngAverageChart.js";
import TeacherTopWrongRate from "./component/Cards/TeacherTopWrongRate.js";
import TeacherTopUserName from "./component/Cards/TeacherTopUserName";
import TeacherEngBarChart from "./component/Cards/TeacherEngBarChart";
import TeacherNormChart from "./component/Cards/TeacherNormChart";
import TeacherEngSidebar from "./component/Sidebar/TeacherEngSidebar";
import TeacherHeader from "./component/Header/TeacherHeader";

export default function TeacherEngMain() {
    const [examNumber, setExamNumber] = React.useState(1);
    function changeExamNumber(examNumber){
        setExamNumber(examNumber);
    }
    return (
        <>
            <TeacherEngSidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <TeacherHeader
                    changeExamNumber={changeExamNumber}
                />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap mt-4">
                        <div className="w-full xl:w-4/12 px-4">
                            <TeacherTopUserName
                                examNumber={examNumber}
                            />
                        </div>
                        <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">
                            <TeacherTopWrongRate
                                examNumber={examNumber}
                            />
                        </div>
                    </div>
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherEngBarChart
                                examNumber={examNumber}
                            />
                        </div>
                    </div>
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherNormChart
                                examNumber={examNumber}
                            />
                        </div>
                    </div>
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherEngAverageChart />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
