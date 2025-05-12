import React from "react";
import LoginForm from "../components/LoginForm";
const Login = () => {
  return (
    <>
      <header className="w-full">
        <nav className="bg-[#FF7700]">
          <div className="flex w-full px-[30px] h-[80px] items-center justify-start max-md:justify-center">
            <img
              src="src/assets/Logo.png"
              alt="Logo Enduro"
              className="w-[120px] h-[77.38px]"
            />
          </div>
        </nav>
      </header>
      <div className="flex items-center justify-center px-4 h-[88vh] 2xl:h-[88vh]">
        <LoginForm />
      </div>
    </>
  );
};
export default Login;
