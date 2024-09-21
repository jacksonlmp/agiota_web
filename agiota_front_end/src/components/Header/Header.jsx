import React, { useState } from 'react';
import { FiUser, FiDollarSign  } from 'react-icons/fi';
import { CiBoxList } from "react-icons/ci";
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate();
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('isAuthenticated');
        window.location.reload();
    };

    const toggleDropdown = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    return (
        <header className="bg-[#141414] text-white shadow-md w-full relative">
            <nav className="max-w-screen-xl mx-auto flex items-center justify-between p-4 w-full">
                <a href="/" className="flex items-center space-x-3">
                    <span className="text-2xl font-bold">Agiota</span>
                </a>
                <div className="flex space-x-4 md:space-x-8 relative">
                    <a
                        href="/Emprestimos"
                        className="flex items-center space-x-2 text-white hover:text-blue-400 transition-colors duration-300"
                        aria-current="page"
                    >
                        <FiDollarSign className="text-lg"/>
                        <span>Empréstimos</span>
                    </a>

                    <button
                        id="dropdownInformationButton"
                        onClick={toggleDropdown} // Toggle dropdown visibility
                        className="text-white hover:text-blue-400 font-medium text-sm px-5 py-2.5 text-center inline-flex items-center"
                        type="button"
                    >
                        <FiUser className="text-lg"/>
                        <span className="ml-3">Perfil</span>
                    </button>


                    <div
                        id="dropdownInformation"
                        className={`${isDropdownOpen ? 'block' : 'hidden'} absolute right-0 mt-2 z-50 bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600`}
                        style={{top: '100%'}} // Garante que o dropdown apareça abaixo do botão
                    >
                        <div className="px-4 py-3 text-sm text-gray-900 dark:text-white">
                            <div>Bonnie Green</div>
                            <div className="font-medium truncate">name@flowbite.com</div>
                        </div>
                        <ul className="py-2 text-sm text-gray-700 dark:text-gray-200"
                            aria-labelledby="dropdownInformationButton">
                            <li>
                                <a href="#"
                                   className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                    Dashboard
                                </a>
                            </li>
                            <li>
                                <a href="#"
                                   className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">
                                    Notificações
                                </a>
                            </li>
                        </ul>
                        <div className="py-2">
                            <a
                                href="#"
                                className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                                onClick={handleLogout}
                            >
                                Sair
                            </a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Header;
