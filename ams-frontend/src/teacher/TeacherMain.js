import React from "react";

// components

import TeacherEngLineChart from "../components/Cards/TeacherEngLineChart.js";
import CardBarChart from "../components/Cards/CardBarChart.js";
import CardPageVisits from "../components/Cards/CardPageVisits.js";
import CardSocialTraffic from "../components/Cards/CardSocialTraffic.js";
import Sidebar from "../components/Sidebar/Sidebar";
import HeaderStats from "../components/Headers/HeaderStats";

export default function TeacherMain() {
    return (
        <>
            <Sidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <HeaderStats />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap">
                        <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">
                            <TeacherEngLineChart />
                        </div>
                        <div className="w-full xl:w-4/12 px-4">
                            <CardBarChart />
                        </div>
                    </div>
                    <div className="flex flex-wrap mt-4">
                        <div className="w-full xl:w-8/12 mb-12 xl:mb-0 px-4">
                            <CardPageVisits />
                        </div>
                        <div className="w-full xl:w-4/12 px-4">
                            <CardSocialTraffic />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
