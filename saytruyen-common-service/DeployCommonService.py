import subprocess


def run_maven_commands(version):
    # Replace the version in the command
    jar_name = f"saytruyen-common-service-{version}"

    try:
        # Maven command to build the project
        subprocess.run(["mvn", "clean", "install"], check=True)
        print("Maven build completed successfully.")

        # GPG command to sign the JAR and POM files
        subprocess.run(["gpg", "-ab", f"./target/{jar_name}.jar"], check=True)
        print(f"Signed {jar_name}.jar successfully.")

        subprocess.run(["gpg", "-ab", f"./target/{jar_name}.pom"], check=True)
        print(f"Signed {jar_name}.pom successfully.")

        subprocess.run(["gpg", "-ab", f"./target/{jar_name}-javadoc.jar"], check=True)
        print(f"Signed {jar_name}-javadoc.jar successfully.")

        subprocess.run(["gpg", "-ab", f"./target/{jar_name}-sources.jar"], check=True)
        print(f"Signed {jar_name}-sources.jar successfully.")

        # Maven command to deploy
        subprocess.run(["mvn", "deploy"], check=True)
        print("Maven deploy completed successfully.")

    except Exception as e:
        print(f"An error occurred: {e}")
        print("Aborting deployment.")


if __name__ == "__main__":
    version = input("Enter the version to deploy: ")
    run_maven_commands(version)
