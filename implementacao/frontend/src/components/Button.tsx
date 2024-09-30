import { ButtonHTMLAttributes } from "react";

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: "primary" | "secondary" | "danger";
}

export default function Button({ variant = "primary", ...props }: ButtonProps) {
  const baseStyles = "px-4 py-2 rounded-md text-white focus:outline-none";
  const variantStyles = {
    primary: "bg-blue-500 hover:bg-blue-600",
    secondary: "bg-gray-500 hover:bg-gray-600",
    danger: "bg-red-500 hover:bg-red-600",
  };

  return (
    <button className={`${baseStyles} ${variantStyles[variant]}`} {...props}>
      {props.children}
    </button>
  );
}
