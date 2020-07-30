# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [0.3.2] - 30/07/2020

### Added
- Currency Field Component Descriptor

## [0.3.1] - 20/04/2020

### Added
- `Interactions.selectInList` for an interaction that selects an entity / entities on a list component

## [0.3.0] - 20/04/2020

### Added
- `StandardScreenTestAPI` for interactions with Screens that are directly extending `com.haulmont.cuba.gui.screen.Screen`
- `UiTestAPI.openStandardScreen()` for opening a Screen that extends `com.haulmont.cuba.gui.screen.Screen`
- `UiTestAPI.getOpenedStandardScreen()` for retrieving a Screen that extends `com.haulmont.cuba.gui.screen.Screen`

### Removed
- BREAKING: `UiTestAPI.getOpenedScreen()` is removed. Use the new `UiTestAPI.getOpenedStandardScreen()` instead

## [0.2.2] - 30/03/2020

### Added
- SideMenu component descriptor

## [0.2.1] - 29/03/2020

### Added
- `ControllableDataServiceProxy.committed(Class<E> entityClass, Predicate<E> entityPredicate)` for asking for an entity that matches a particular criteria
- `Interactions.closeInputDialogWith(CloseAction closeAction)` for defining the close action of the input dialog

## [0.2.0] - 02/03/2020

### Added
- Initial Release

### Dependencies
- CUBA 7.2.x

