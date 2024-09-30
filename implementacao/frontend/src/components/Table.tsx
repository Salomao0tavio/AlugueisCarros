import { ReactNode } from "react";
import "../styles/globals.css";

interface TableProps {
  headers: string[];
  rows: ReactNode[];
}

export default function Table({ headers, rows }: TableProps) {
  return (
    <table className="table-auto w-full border-collapse border border-gray-300">
      <thead>
        <tr className="bg-gray-200">
          {headers.map((header, index) => (
            <th key={index} className="border px-4 py-2">
              {header}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {rows.map((row, index) => (
          <tr key={index} className="text-center">
            {row}
          </tr>
        ))}
      </tbody>
    </table>
  );
}
