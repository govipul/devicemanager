Device Manager REST service - recruitment task
==============================================

Create a simple REST service (no user interface required) for tracking status of
devices.

Use cases
---------

The service should support following use cases:

Device registration
    Each device has an associated human-friendly name, secret key and status.
    Client provides name and secret key, while the status is initially set to
    NEW.

Retrieval of device details
    Returns name, secret key and current status for a given device.

Listing devices
    Includes names and status of devices, but not secret keys

Device status update
    Client can set device status to OK or UNHEALTHY, but he must provide secret
    key associated with the device as part of the request. Otherwise update
    attempt is rejected.

    If device status is OK, but it has not been refreshed by API call during
    last 5 minutes, than device status changes to STALE.

Listing devices by status
    Same as 'Listing devices' but only devices with required status are returned.

Note:

- Please follow REST API design best practices.

- No authentication/authorization is required for making API calls.

- Plain HTTP is enough.

- Restarting application should cause loss of data, there should be no
  persistence layer (database, Hibernate etc.)
  
- Please keep the solution simple, don't include code that is not related to
  solving requested use-cases.

Technical requirements
----------------------

- Service should be implemented in Java, and it should run on Linux.

- Code quality matters. In particular, including automated tests at appropriate
  layers is expected.

- Either Maven or Gradle should be used as build system.

- Scripts for compiling and running the service should be included.

- Any free libraries/frameworks that Maven/Gradle can download from public
  repositories is allowed.

- Building and running the system should not require external system
  dependencies apart from basic ones like Java, Maven/Gradle or Docker.

Delivery
--------

The code should be commited to this repository.

If you have questions about use-cases, technical requirements or delivery,
please just ask.

Confidentiality
---------------

Please don't share the details of the task with other potential candidates ;)
