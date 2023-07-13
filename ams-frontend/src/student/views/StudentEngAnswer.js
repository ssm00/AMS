import React from "react";

// components


import StudentEngAnswerForm from "../component/AnswerInput/StudentEngAnswerForm";
import TeacherEngSidebar from "../../teacher/component/Sidebar/TeacherEngSidebar";
import TeacherHeaderStats from "../../teacher/component/Header/TeacherHeaderStats";

export default function StudentEngAnswer() {
    return (
        <>
            <TeacherEngSidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <TeacherHeaderStats />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <StudentEngAnswerForm
                                examNumber="2"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
