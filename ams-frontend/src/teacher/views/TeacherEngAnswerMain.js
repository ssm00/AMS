import React from "react";

// components


import TeacherAnswerForm from "../component/AnswerInput/TeacherAnswerForm";
import TeacherEngSidebar from "../component/Sidebar/TeacherEngSidebar";
import TeacherHeaderStats from "../component/Header/TeacherHeaderStats";

export default function TeacherEngAnswerMain() {
    const [examNumber, setExamNumber] = React.useState(1);
    function changeExamNumber(examNumber){
        setExamNumber(examNumber);
    }
    return (
        <>
            <TeacherEngSidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <TeacherHeaderStats
                    changeExamNumber={changeExamNumber}
                />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherAnswerForm
                                examNumber={examNumber}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
