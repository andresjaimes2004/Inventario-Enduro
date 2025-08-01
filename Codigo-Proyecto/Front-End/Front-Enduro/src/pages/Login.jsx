import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/authService";
import {
  AiOutlineEye,
  AiOutlineEyeInvisible,
  AiOutlineExclamationCircle,
} from "react-icons/ai";

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [isFocused, setIsFocused] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    let valid = true;

    if (!username) {
      setUsernameError("Ingresa el usuario.");
      valid = false;
    } else {
      setUsernameError("");
    }

    if (!password) {
      setPasswordError("Ingresa la contraseña.");
      valid = false;
    } else {
      setPasswordError("");
    }

    if (!valid) return;

    navigate("/Inicio");
    // try {
    //   const result = await login(username, password);
    //   if (result === true || result === "true") {
    //     navigate("/Inicio");
    //   } else {
    //     alert("Usuario o contraseña incorrectos.");
    //   }
    // } catch (error) {
    //   alert("Error al conectar con el servidor.");
    // }
  };

  return (
    <>
      <header className="w-full">
        <nav className="bg-[#FF7700]">
          <div className="flex w-full px-4 h-[80px] items-center justify-start max-md:justify-center">
            <img
              src="src/assets/Logo.png"
              alt="Logo Enduro"
              className="w-[100px] md:w-[120px] h-auto mt-0"
            />
          </div>
        </nav>
      </header>
      {/* Login */}
      <section className="flex items-center justify-center px-4 h-[88vh] 2xl:h-[88vh]">
        <div
          className="flex w-[820px] h-[400px] max-md:w-full max-md:h-[90%] justify-center items-center
            bg-[#AFCBD3] rounded-[20px] gap-5 p-20 max-md:!flex-col max-md:p-10 max-sm:items-start max-sm:gap-2"
        >
          <div className="flex flex-col w-[50%] justify-center items-center gap-10 max-md:w-full">
            <div className="flex flex-col w-full max-w-[350px] justify-center items-center gap-4 mx-auto">
              <h2 className="text-3xl text-[#FF7700] font-bold text-center w-full">
                L O G I N
              </h2>
              <div className="w-full flex flex-col gap-1">
                <div className="flex items-center w-full">
                  <div className="relative w-full">
                    <input
                      type="text"
                      placeholder="USERNAME"
                      className={`text-center text-xl text-black bg-white rounded-2xl w-full h-[45px] pl-10 pr-10 ${
                        usernameError ? "border-2 border-red-500" : ""
                      }`}
                      value={username}
                      onChange={(e) => {
                        setUsername(e.target.value);
                        if (e.target.value) setUsernameError("");
                      }}
                    />
                    {usernameError && (
                      <AiOutlineExclamationCircle
                        className="absolute right-3 top-1/2 transform -translate-y-1/2 text-red-500"
                        size={22}
                      />
                    )}
                  </div>
                </div>
              </div>
              <div className="w-full flex flex-col gap-1">
                <div className="flex items-center w-full">
                  <div className="relative w-full">
                    <input
                      type={showPassword ? "text" : "password"}
                      placeholder="●●●●●●●"
                      autoComplete="new-password"
                      inputMode="none"
                      className={`text-center text-xl text-black bg-white rounded-2xl w-full h-[45px] pl-10 pr-10 password-input ${
                        passwordError ? "border-2 border-red-500" : ""
                      }`}
                      onFocus={() => setIsFocused(true)}
                      onBlur={() => setIsFocused(false)}
                      value={password}
                      onChange={(e) => {
                        setPassword(e.target.value);
                        if (e.target.value) setPasswordError("");
                      }}
                    />
                    {isFocused && (
                      <button
                        type="button"
                        onMouseDown={(e) => e.preventDefault()}
                        onClick={togglePasswordVisibility}
                        className="absolute right-10 top-1/2 transform -translate-y-1/2 text-gray-500"
                        style={{ pointerEvents: "auto" }}
                      >
                        {showPassword ? (
                          <AiOutlineEyeInvisible size={20} />
                        ) : (
                          <AiOutlineEye size={20} />
                        )}
                      </button>
                    )}
                    {passwordError && (
                      <AiOutlineExclamationCircle
                        className="absolute right-3 top-1/2 transform -translate-y-1/2 text-red-500"
                        size={22}
                      />
                    )}
                  </div>
                </div>
              </div>
              <button
                className="bg-[#FF7700] cursor-pointer text-white items-center justify-center mt-6 w-full h-[45px] rounded-2xl flex"
                onClick={handleLogin}
                style={{ maxWidth: "100%" }}
              >
                I N G R E S A R
              </button>
            </div>
          </div>
          <div className="flex flex-col w-[50%] justify-center items-center max-md:w-full">
            <img
              src="src/assets/Login.svg"
              alt="Login Illustration"
              className="w-[400px] h-[250px] max-md:w-full"
            />
          </div>
        </div>
      </section>
      {/* End Login */}
    </>
  );
};
export default Login;
