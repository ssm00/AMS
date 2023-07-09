import React from "react";

// components


import Sidebar from "../components/Sidebar/Sidebar";
import HeaderStats from "../components/Headers/HeaderStats";
import TeacherAnswerForm from "../components/Cards/TeacherAnswerForm";

export default function TeacherAnswer() {
    return (
        <>
            <Sidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <HeaderStats />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherAnswerForm />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
