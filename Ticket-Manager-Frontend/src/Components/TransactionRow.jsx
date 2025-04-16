import React from "react";

function TransactionRow({ transaction }) {
  return (
    <tr>
      <td>{transaction.id}</td>
      <td>{transaction.transactionType}</td>
      <td>{transaction.name}</td>
      <td>{transaction.ticketCount}</td>
      <td>{transaction.currentPoolSize}</td>
    </tr>
  );
}

export default TransactionRow;
