import React, {useState} from "react";
import Chart from "chart.js";
import {call} from "../../../service/ApiService";

export default function TeacherNormChart(props) {
  const [data, setData] = useState([]);
  let normConfig = {
    type: "line",
    data: {
      labels: [
      ],
      datasets: [
        {
          label: "명",
          backgroundColor: "#ed64a6",
          borderColor: "#ed64a6",
          data: [],
          fill: false,
          barThickness: 8,
          tension: 0.3,
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      responsive: true,
      title: {
        display: false,
        text: "Orders Chart",
      },
      tooltips: {
        mode: "index",
        intersect: false,
      },
      hover: {
        mode: "nearest",
        intersect: true,
      },
      legend: {
        labels: {
          fontColor: "rgba(0,0,0)",
        },
        align: "end",
        position: "bottom",
      },
      scales: {
        x: {
          type: 'linear',
          bounds: 'data'
        },
        xAxes: [
          {
            ticks: {
              fontColor: "rgba(0,0,0)",
              min: 0,
              max: 100,
              stepSize: 10,
            },
            scaleLabel: {
              display: false,
              labelString: "Value",
            },
          }
        ],
        yAxes: [
          {
            ticks: {
              fontColor: "rgba(0,0,0)",
              stepSize: 1,
              suggestedMax : 3,
              min: 0,
            },
            display: true,
            scaleLabel: {
              display: false,
              labelString: "Value",
            },
            gridLines: {
              borderDash: [2],
              drawBorder: false,
              borderDashOffset: [2],
              color: "rgba(0,0,0,.4)",
              zeroLineColor: "rgba(0,0,0,.4)",
              zeroLineBorderDash: [2],
              zeroLineBorderDashOffset: [2],
            },
          },
        ],
      },
      annotation: {
        annotations: [{
          type: 'line',
          mode: 'horizontal',
          scaleID: 'x-axis-0',
          value: 5,
          borderColor: 'rgb(75, 192, 192)',
          borderWidth: 4,
          label: {
            enabled: false,
            content: 'Test label'
          }
        }]
      }
    },
  };
  let array = new Array(
      {key : 10,
            value: 0},
      {key : 20,
        value: 0},
      {key : 30,
        value: 0},
      {key : 40,
        value: 0},
      {key : 50,
        value: 0},
      {key : 60,
        value: 0},
      {key : 70,
        value: 0},
      {key : 80,
        value: 0},
      {key : 90,
        value: 0},
      {key : 100,
        value: 0},
  );
  React.useEffect(() => {
    call("/teachers/distributionCount-table", "POST", {"grade" : 3, "examNumber" : props.examNumber ,"examSubject" : props.examSubject}).then((response) => {
      for (let i = 0; i < response.result.eachStudentScoreCountList.length; i++) {
        if(array.includes(response.result.eachStudentScoreCountList[i].score)){
            array.find((element) => element.key === response.result.eachStudentScoreCountList[i].score).value = response.result.eachStudentScoreCountList[i].count;
        }else{
            array.push({key : response.result.eachStudentScoreCountList[i].score, value : response.result.eachStudentScoreCountList[i].count});
        }
      }
      array.sort((a,b) => a.key-b.key);
      for(let i = 0; i < array.length; i++){
        normConfig.data.labels.push(array[i].key);
        normConfig.data.datasets[0].data.push(array[i].value);
      }
      var ctx = document.getElementById("eng-norm-chart").getContext("2d");
      window.myLine = new Chart(ctx, normConfig);
    }).catch((error) => {
      return;
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [props.examNumber]);
  return (
    <>
      <div className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-lg rounded">
        <div className="rounded-t mb-0 px-4 py-3 bg-transparent">
          <div className="flex flex-wrap items-center">
            <div className="relative w-full max-w-full flex-grow flex-1">
              <h6 className="uppercase text-blueGray-500 mb-1 text-xs font-semibold">
                성적 그래프
              </h6>
              <h2 className="text-blueGray-700 text-xl font-semibold">
                {props.examNumber}회차 성적 정규 분포
              </h2>
            </div>
          </div>
        </div>
        <div className="p-4 flex-auto">
          {/* Chart */}
          <div className="relative h-350-px">
            <canvas id="eng-norm-chart"></canvas>
          </div>
        </div>
      </div>
    </>
  );
}
