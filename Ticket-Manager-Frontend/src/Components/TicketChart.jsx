import React, { useEffect, useState } from "react";
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement } from "chart.js";
import { Line } from "react-chartjs-2";
import axios from "axios";
import "./TicketChart.css"; // Import the updated CSS

// Register required Chart.js components
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement
);

const TicketChart = () => {
  // Proper state initialization
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [
      {
        label: "Tickets Released",
        data: [],
        borderColor: "rgb(53, 162, 235)",
        backgroundColor: "rgba(53, 162, 235, 0.5)",
      },
      {
        label: "Tickets Purchased",
        data: [],
        borderColor: "rgb(255, 99, 132)",
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
    ],
  });

  useEffect(() => {
    const interval = setInterval(async () => {
      try {
        const response = await axios.get("http://localhost:8081/api/tickets/graph-data");
        const { timestamps, ticketsReleased, ticketsPurchased } = response.data;

        // Normalize timestamps to start from 0
        const startTime = timestamps.length > 0 ? timestamps[0] : 0;
        const normalizedTimestamps = timestamps.map((timestamp) => timestamp - startTime);

        // Update state correctly
        setChartData(prev => ({
          ...prev,
          labels: normalizedTimestamps,
          datasets: [
            { ...prev.datasets[0], data: ticketsReleased },
            { ...prev.datasets[1], data: ticketsPurchased }
          ]
        }));
      } catch (error) {
        console.error("Error fetching chart data:", error);
      }
    }, 2000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="chart-container">
      <h2>Tickets Released vs Purchased</h2>
      <Line
        data={chartData}
        options={{
          responsive: true,
          plugins: {
            legend: {
              position: 'top',
              labels: {
                color: '#fff', // Legend text color
              },
            },
            title: {
              display: true,
              text: 'Ticket Activity',
              color: '#fff', // Title text color
            },
          },
          scales: {
            x: {
              type: 'linear', // Use linear scale for the x-axis
              title: {
                display: true,
                text: 'Time (seconds)',
                color: '#fff', // X-axis title color
              },
              grid: {
                color: '#444', // X-axis grid color
              },
              ticks: {
                color: '#fff', // X-axis tick color
              },
              beginAtZero: true, // Ensure the x-axis starts at 0
            },
            y: {
              type: 'linear',
              title: {
                display: true,
                text: 'Ticket Count',
                color: '#fff', // Y-axis title color
              },
              grid: {
                color: '#444', // Y-axis grid color
              },
              ticks: {
                color: '#fff', // Y-axis tick color
              },
              beginAtZero: true, // Ensure the y-axis starts at 0
            },
          },
        }}
      />
    </div>
  );
};

export default TicketChart;

