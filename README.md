# 🛠️ HAPI Scripts for Atlassian Administration

This repository contains a curated collection of reusable and modular [HAPI (Happy Atlassian Plugin Interface)](https://docs.adaptavist.com/hapi/latest/) scripts designed to automate and simplify administrative tasks for Atlassian products such as Jira Cloud and Jira Data Center.

## 📦 Contents

Each script is organized by use case and includes inline documentation for ease of understanding and reuse.

```
├── README.md
├── workflows/ # Workflow-related scripts (e.g., scheme migration)
├── permissions/ # Scripts for managing permission schemes
├── projects/ # Project configuration scripts
├── custom-fields/ # Custom field creation and management
├── users-groups/ # User and group management
├── issue-operations/ # Bulk issue update, transitions, field manipulations
└── utils/ # Helper functions and shared utilities
```

## ✅ Requirements

- Jira instance (Cloud or Data Center)
- [ScriptRunner](https://www.adaptavist.com/products/scriptrunner) installed and configured
- Appropriate administrative permissions to run scripts
- Familiarity with [HAPI syntax](https://docs.adaptavist.com/hapi/latest/syntax/)

## 🚀 Getting Started

To run a script:

1. Open the Script Console in your Jira instance.
2. Copy and paste the desired script from this repo.
3. Update any placeholder values as needed.
4. Execute the script and verify the output.

> ⚠️ **Warning**: Always test scripts in a staging environment before using them in production.

🤝 Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request with your scripts, enhancements, or bug fixes.

1. Fork the repo

2. Create your feature branch: git checkout -b feature/my-script

3. Commit your changes: git commit -am 'Add new HAPI script'

4. Push to the branch: git push origin feature/my-script

5. Open a pull request

📄 License
This project is licensed under the MIT License – see the LICENSE file for details.
