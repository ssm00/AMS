import React from "react";

// components

import TeacherEngSidebar from "../component/Sidebar/TeacherEngSidebar";
import TeacherHeader from "../component/Header/TeacherHeader";
import TeacherModifyForm from "../component/AnswerInput/TeacherModifyForm";

export default function TeacherEngModifyMain() {
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
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherModifyForm
                                examNumber={examNumber}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
