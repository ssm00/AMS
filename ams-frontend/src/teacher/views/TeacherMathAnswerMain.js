import React from "react";

// components


import TeacherAnswerForm from "../component/AnswerInput/TeacherAnswerForm";
import TeacherEngSidebar from "../component/Sidebar/TeacherEngSidebar";
import TeacherAnswerHeader from "../component/Header/TeacherAnswerHeader";
import {call} from "../../service/ApiService";

export default function TeacherMathAnswerMain() {
    const [examNumber, setExamNumber] = React.useState(1);
    call("/teachers/average-graph", "POST", {"grade" : 3, "examSubject" : "MATH"}).then((response) => {
        setExamNumber(response.result.eachAverageScoreList.length+1);
    }).catch((error) => {
        return;
    });
    return (
        <>
            <TeacherEngSidebar />
            <div className="relative md:ml-64 bg-blueGray-100">
                <TeacherAnswerHeader
                    examNumber={examNumber}
                    statTitle="수학"
                />
                <div className="px-4 md:px-10 mx-auto w-full -m-24">
                    <div className="flex flex-wrap">
                        <div className="w-full mb-12 xl:mb-0 px-4">
                            <TeacherAnswerForm
                                color="light"
                                examNumber={examNumber}
                                examSubject="MATH"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}
