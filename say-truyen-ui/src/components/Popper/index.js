import clsx from "clsx";
import styles from "./Popper.module.scss";

const Popper = ({ children }) => {
	return <div className={clsx(styles.wrapper)}>{children}</div>;
};

export default Popper;
