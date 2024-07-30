import { useState } from "react";
import clsx from "clsx";
import { Link } from "react-router-dom";
import Tippy from "@tippyjs/react/headless";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronLeft } from "@fortawesome/free-solid-svg-icons";

import styles from "./Menu.module.scss";
import Popper from "~/components/Popper";

const Menu = (props) => {
	const [history, setHistory] = useState([{ data: props.items }]);
	let current = history[history.length - 1];

	const handleMultipleMenu = (item) => {
		const isParent = !!item.children;
		if (isParent) {
			setHistory((prev) => [...prev, { data: item.children.value }]);
		}
	};

	const renderMenuItem = () => {
		return current.data.map((item, index) => {
			return item?.to === undefined ? (
				<div
					key={index}
					className={clsx(styles.option)}
					onClick={() => handleMultipleMenu(item)}
				>
					{item?.icon}
					{item?.name}
				</div>
			) : (
				<Link to={item.to} key={index} className={clsx(styles.option)}>
					{item?.icon}
					{item?.name}
				</Link>
			);
		});
	};

	return (
		<Tippy
			onHide={() => setHistory(history.slice(0, 1))}
			unmountHTMLWhenHide={true}
			interactive={true}
			delay={[0, 700]}
			placement="bottom-end"
			render={(attrs) => (
				<div
					className={clsx(styles.menuOption)}
					tabIndex="-1"
					{...attrs}
				>
					<Popper>
						{history.length > 1 && (
							<header>
								<div className={clsx(styles.header)}>
									<FontAwesomeIcon
										className={clsx(styles.icon)}
										icon={faChevronLeft}
										onClick={() =>
											setHistory(
												history.slice(
													0,
													history.length - 1
												)
											)
										}
									/>
									<h4>
										{
											history[history.length - 1].data[0]
												.type
										}
									</h4>
								</div>
							</header>
						)}
						{renderMenuItem()}
					</Popper>
				</div>
			)}
		>
			{props.children}
		</Tippy>
	);
};

export default Menu;
