import { faCheckCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Tippy from "@tippyjs/react/headless";
import clsx from "clsx";
import { useEffect, useState } from "react";
import Button from "../Button";
import Popper from "../Popper";
import styles from "./AccountItem.module.scss";

const AccountItem = (props) => {
	const [account, setAccount] = useState({});

	useEffect(() => {
		const { accountInfo } = props;
		setAccount(accountInfo);
	}, [account]);

	return (
		<Tippy
			disabled={!props?.hover}
			interactive={true}
			delay={[200, 0]}
			placement="bottom-start"
			render={(attrs) => (
				<div
					tabIndex="-1"
					{...attrs}
					className={clsx(styles.detailAccountContainer)}
				>
					<Popper>
						<div className={clsx(styles.detailAccount)}>
							<div className={clsx(styles.avatarContainer)}>
								<img
									className={clsx(styles.avatar)}
									src={account.images}
									alt={account.fullname}
								/>
								<div className={clsx(styles.btnContainer)}>
									<Button primary>Follow</Button>
								</div>
							</div>
							<div className={clsx(styles.info)}>
								<h4 className={clsx(styles.username)}>
									<span>{account.username}</span>
									{account.isTick && (
										<FontAwesomeIcon
											className={clsx(styles.check)}
											icon={faCheckCircle}
										/>
									)}
								</h4>
								<span className={clsx(styles.fullname)}>
									{account.fullname}
								</span>
							</div>
							<div className={clsx(styles.userStat)}>
								{account.followers} Followers -- {account.likes}{" "}
								Likes
							</div>
						</div>
					</Popper>
				</div>
			)}
		>
			<div className={clsx(styles.wrapper)}>
				<img
					className={clsx(styles.avatar)}
					src={account.images}
					alt={account.fullname}
				/>
				<div className={clsx(styles.info)}>
					<h4 className={clsx(styles.username)}>
						<span>{account.username}</span>
						{account.isTick && (
							<FontAwesomeIcon
								className={clsx(styles.check)}
								icon={faCheckCircle}
							/>
						)}
					</h4>
					<span className={clsx(styles.fullname)}>
						{account.fullname}
					</span>
				</div>
			</div>
		</Tippy>
	);
};

export default AccountItem;
