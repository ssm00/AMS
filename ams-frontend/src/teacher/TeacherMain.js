import React from "react";

// components

import TeacherEngLineChart from "../components/Cards/TeacherEngLineChart.js";
import TeacherTopWrongRate from "../components/Cards/TeacherTopWrongRate.js";
import Sidebar from "../components/Sidebar/Sidebar";
import HeaderStats from "../components/Headers/HeaderStats";
import TeacherTopUserName from "../components/Cards/TeacherTopUserName";
import TeacherEngBarChart from "../components/Cards/TeacherEngBarChart";
import TeacherNormChart from "../components/Cards/TeacherNormChart";

export default function TeacherMain() {
    return (
        <>
            <Sidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <HeaderStats />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap mt-4">
                        <div className="w-full xl:w-4/12 px-4">
                            <TeacherTopUserName
                                examNumber={2}
                            />
                        </div>
                        <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">
                            <TeacherTopWrongRate
                                examNumber={2}
                            />
                        </div>
                    </div>
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherEngBarChart
                                examNumber={2}
                            />
                        </div>
                    </div>
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherNormChart
                                examNumber={2}
                            />
                        </div>
                    </div>
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherEngLineChart />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
