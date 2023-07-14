import React from "react";

// components


import TeacherAnswerForm from "../component/AnswerInput/TeacherAnswerForm";
import TeacherEngSidebar from "../component/Sidebar/TeacherEngSidebar";
import TeacherAnswerHeader from "../component/Header/TeacherAnswerHeader";
import {call} from "../../service/ApiService";

export default function TeacherEngAnswerMain() {
    const [examNumber, setExamNumber] = React.useState(1);
    call("/teachers/average-graph", "POST", {"grade" : 3, "examSubject" : "ENGLISH"}).then((response) => {
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
