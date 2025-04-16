import React, { useEffect, useState } from "react";
import axios from "axios";
import "./RealTimeLogs.css";

const RealTimeLogs = () => {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    const interval = setInterval(async () => {
      try {
        const response = await axios.get("http://localhost:8081/api/logs"); 
        setLogs(response.data);
      } catch (error) {
        console.error("Error fetching logs:", error);
      }
    }, 1000); // Poll logs every 1 second

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="real-time-logs">
      <h2>Real-Time Logs</h2>
      <div className="logs-container">
        {logs.map((log, index) => (
          <p key={index}>{log}</p>
        ))}
      </div>
    </div>
  );
};

export default RealTimeLogs;

