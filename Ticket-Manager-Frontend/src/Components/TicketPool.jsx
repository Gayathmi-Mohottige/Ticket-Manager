import React from "react";
import "./SimulationForm";

const TicketPool = ({ poolSize, maxPool }) => {
  return (
    <div className="ticket-pool">
      <h2>Current Ticket Pool</h2>
      <meter
        min="0"
        max={parseInt(maxPool, 10)} 
        value={parseInt(poolSize, 10)} 
      ></meter>
      <p>
        {poolSize} tickets available out of {maxPool}.
      </p>
    </div>
  );
};

export default TicketPool;
