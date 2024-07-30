import clsx from "clsx";
import { useEffect, useState } from "react";

import styles from "./LoginModal.module.scss";
import { images } from "~/assets/images";
import Button from "~/components/Button";
import Popper from "~/components/Popper";

const LOGIN_TYPES = [
	{
		icon: <img src={images.qrIcon} alt="qrcode" />,
		code: "qrcode",
		name: "Use QR Code",
	},
	{
		icon: <img src={images.accountIcon} alt="account" />,
		code: "account",
		name: "Use phone / email / username",
	},
	{
		icon: <img src={images.facebookIcon} alt="facebook" />,
		code: "facebook",
		name: "Continue with Facebook",
	},
	{
		icon: <img src={images.googleIcon} alt="google" />,
		code: "google",
		name: "Continue with Google",
	},
	{
		icon: <img src={images.twitterIcon} alt="twitter" />,
		code: "twitter",
		name: "Continue with Twitter",
	},
	{
		icon: <img src={images.lineIcon} alt="line" />,
		code: "line",
		name: "Continue with LINE",
	},
	{
		icon: <img src={images.talkIcon} alt="talk" />,
		code: "talk",
		name: "Continue with KakaoTalk",
	},
	{
		icon: <img src={images.appleIcon} alt="apple" />,
		code: "apple",
		name: "Continue with Apple",
	},
	{
		icon: <img src={images.instagramIcon} alt="instagram" />,
		code: "instagram",
		name: "Continue with Instagram",
	},
];

const LoginModal = (props) => {
	const [isSignUp, setIsSignUp] = useState(false);

	const handleSignUp = () => {
		setIsSignUp(!isSignUp);
	};

	return (
		<div className={clsx(styles.wrapper)}>
			<div className={clsx(styles.container)}>
				<div className={clsx(styles.popperContainer)}>
					<Popper>
						{props.children}
						<h2>
							{isSignUp === false
								? "Log in to TikTok"
								: "Sign up for Tiktok"}
						</h2>
						{LOGIN_TYPES.map((item, index) => {
							if (isSignUp === true && item.code === "qrcode") {
								return "";
							}

							return index !== LOGIN_TYPES.length - 1 ? (
								<div
									className={clsx(styles.loginBtnContainer)}
									key={index}
								>
									<Button default>
										{item.icon} {item.name}
									</Button>
								</div>
							) : (
								<div
									className={clsx(
										styles.loginBtnContainer,
										styles.mgb80
									)}
									key={index}
								>
									<Button default>
										{item.icon} {item.name}
									</Button>
								</div>
							);
						})}
						<div className={clsx(styles.subContainer)}>
							<div>
								{isSignUp === false
									? "Don't have an account?"
									: "Already have an account?"}
							</div>
							<div
								className={clsx(styles.text)}
								onClick={handleSignUp}
							>
								{isSignUp === false ? "Sign up" : "Log in"}
							</div>
						</div>
					</Popper>
				</div>
			</div>
		</div>
	);
};

export default LoginModal;
