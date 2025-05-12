import React from "react";

const LoginForm = () => {
  return (
    <div
      className="flex w-[820px] h-[400px] max-md:w-full max-md:h-[90%] justify-center items-center
     bg-[#AFCBD3] rounded-[20px] gap-5 p-20 max-md:!flex-col max-md:p-10"
    >
      <div className="flex flex-col w-[50%] justify-center items-center gap-10 max-md:w-full">
        <div className="flex flex-col justify-center items-center gap-4 max-md:w-full">
          <h2 className="text-3xl text-[#FF7700] font-bold">L O G I N</h2>
          <input
            type="text"
            placeholder="USERNAME"
            className="text-center text-xl text-black bg-white rounded-2xl w-full h-[45px]"
          />
          <input
            type="password"
            placeholder="●●●●●●"
            className="text-center text-4xl text-black bg-white rounded-2xl w-full h-[45px]"
          />
        </div>
        <button className="bg-[#FF7700] cursor-pointer text-white items-center justify-center w-full h-[45px] rounded-2xl flex">
          <a href="#">I N G R E S A R</a>
        </button>
      </div>
      <div className="flex flex-col w-[50%] justify-center items-center max-md:w-full">
        <img
          src="src/assets/Login.svg"
          alt="Login Illustration"
          className="w-[400px] h-[250px] max-md:w-full"
        />
      </div>
    </div>
  );
};

export default LoginForm;
