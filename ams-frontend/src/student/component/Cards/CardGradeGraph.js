import React, {useState} from "react";
import Chart from "chart.js";
import {call} from "../../../service/ApiService.js";

export default function CardGradeGraph({examSubject}) {
  const [data, setData] = useState([]);

    var config = {
      type: "line",
      data: {
        labels: [],
        datasets: [
          {
            label: '점수',
            backgroundColor: "#FFF",
            borderColor: "#FFF",
            data: [],
            fill: false,
          },
          {
            label: '등수',
            fill: false,
            backgroundColor: "#334155",
            borderColor: "#334155",
            data: [],
          },
          {
            label: '총인원',
            fill: false,
            backgroundColor: "#334155",
            borderColor: "#334155",
            data: [],
          },
        ],
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        title: {
          display: false,
          text: "Sales Charts",
          fontColor: "white",
        },
        legend: {
          display: false,
          labels: {
            fontColor: "white",
          },
          align: "end",
          position: "bottom",
        },
        tooltips: {
          mode: "index",
          intersect: true,
        },
        hover: {
          mode: "nearest",
          intersect: true,
        },
        scales: {
          xAxes: [
            {
              ticks: {
                fontColor: "rgba(255,255,255,.7)",
              },
              display: true,
              scaleLabel: {
                display: false,
                labelString: "Month",
                fontColor: "white",
              },
              gridLines: {
                display: false,
                borderDash: [2],
                borderDashOffset: [2],
                color: "rgba(33, 37, 41, 0.3)",
                zeroLineColor: "rgba(0, 0, 0, 0)",
                zeroLineBorderDash: [2],
                zeroLineBorderDashOffset: [2],
              },
            },
          ],
          yAxes: [
            {
              ticks: {
                fontColor: "rgba(255,255,255,.7)",
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
                color: "rgba(255, 255, 255, 0.15)",
                zeroLineColor: "rgba(33, 37, 41, 0)",
                zeroLineBorderDash: [2],
                zeroLineBorderDashOffset: [2],
              },
            },
          ],
        },
      },
    };
    React.useEffect(() => {
      call("/users/grade-graph", "GET", null).then((response) => {
        setData(response.result.eachExamNumberInfos);
        for(let i=0; i < response.result.eachExamNumberInfos.length; i++){
          if(response.result.eachExamNumberInfos[i].subject === examSubject) {
            config.data.labels.push(response.result.eachExamNumberInfos[i].examNumber + "회차");
            config.data.datasets[0].data.push(response.result.eachExamNumberInfos[i].studentScore);
            config.data.datasets[1].data.push(response.result.eachExamNumberInfos[i].studentRank);
            config.data.datasets[2].data.push(response.result.eachExamNumberInfos[i].totalStudents);
          }
        }
        var ctx = document.getElementById("line-chart").getContext("2d");
        window.myLine = new Chart(ctx, config);
      }).catch((error) => {
        return;
      });

  }, []);
  return (
    <>
      <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-blueGray-700">
        <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
          <div className="flex flex-wrap items-center">
            <div className="relative w-full max-w-full flex-grow flex-1">
              <h6 className="uppercase text-blueGray-100 mb-1 text-xs font-semibold">
                성적 그래프
              </h6>
              <h2 className="text-white text-xl font-semibold">{examSubject === "ENGLISH" ? "영어" : "수학"}</h2>
            </div>
          </div>
        </div>
        <div className="p-4 flex-auto">
          {/* Chart */}
          <div className="relative h-350-px">
            <canvas id="line-chart"></canvas>
          </div>
        </div>
      </div>
    </>
  );
}
