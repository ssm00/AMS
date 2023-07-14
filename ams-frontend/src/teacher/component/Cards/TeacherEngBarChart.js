import React, {useState} from "react";
import Chart from "chart.js";
import {call} from "../../../service/ApiService";

export  default function TeacherEngBarChart({examNumber}) {
  const [data, setData] = useState([]);
  var barConfig = {
    type: "bar",
    data: {
      //x축
      labels: [
      ],
      datasets: [
        {
          //이름
          label: "점수",
          backgroundColor: "#4c51bf",
          borderColor: "#4c51bf",
          //x축에 대한 데이터(y값)
          data: [],
          fill: false,
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      responsive: true,
      title: {
        display: false,
        text: "Average Charts",
        fontColor: "white",
      },
      legend: {
        labels: {
          fontColor: "black",
        },
        align: "end",
        position: "bottom",
      },
      tooltips: {
        mode: "index",
        intersect: false,
      },
      hover: {
        mode: "nearest",
        intersect: true,
      },
      scales: {
        xAxes: [
          {
            ticks: {
              fontColor: "rgba(0,0,0)",
            },
            display: true,
            scaleLabel: {
              display: false,
              labelString: "1번",
              fontColor: "white",
            },
            gridLines: {
              display: false,
              borderDash: [2],
              borderDashOffset: [2],
              color: "rgba(0,0,0,.4)",
              zeroLineColor: "rgba(0,0,0,.4)",
              zeroLineBorderDash: [2],
              zeroLineBorderDashOffset: [2],
            },
          },
        ],
        yAxes: [
          {
            ticks: {
              fontColor: "rgba(0,0,0)",
              stepSize: 10,
              max: 100,
              min: 0,
            },
            display: true,
            scaleLabel: {
              display: false,
              labelString: "Value",
              fontColor: "white",
            },
            gridLines: {
              borderDash: [3],
              borderDashOffset: [3],
              drawBorder: false,
              color: "rgba(0,0,0,.4)",
              zeroLineColor: "rgba(0,0,0,.4)",
              zeroLineBorderDash: [2],
              zeroLineBorderDashOffset: [2],
            },
          },
        ],
      },
    },
  };
  React.useEffect(() => {
    call("/teachers/distribution-table", "POST", {"grade" : 3, "examNumber" : examNumber ,"examSubject" : "ENGLISH"}).then((response) => {
      for (let i = 0; i < response.result.eachStudentScoreList.length; i++) {
        barConfig.data.labels.push(response.result.eachStudentScoreList[i].name);
        barConfig.data.datasets[0].data.push(response.result.eachStudentScoreList[i].studentScore);
      }
      var ctx = document.getElementById("eng-bar-chart").getContext("2d");
      window.myLine = new Chart(ctx, barConfig);
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [examNumber]);
  return (
    <>
      <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-white">
        <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
          <div className="flex flex-wrap items-center">
            <div className="relative w-full max-w-full flex-grow flex-1">
              <h6 className="uppercase text-blueGray-500 mb-1 text-xs font-semibold">
                성적 그래프
              </h6>
              <h2 className="text-blueGray-700 text-xl font-semibold">{examNumber}회차 성적 그래프</h2>
            </div>
          </div>
        </div>
        <div className="p-4 flex-auto">
          {/* Chart */}
          <div className="relative h-350-px">
            <canvas id="eng-bar-chart"></canvas>
          </div>
        </div>
      </div>
    </>
  );
}
